package com.sh.petking.room.controller;

import com.sh.petking.room.model.entity.RoomAttach;
import com.sh.petking.room.model.service.RoomService;
import com.sh.petking.room.model.vo.RoomVo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 0102 객실정보 수정
 */
@WebServlet("/room/roomUpdate")
public class RoomUpdateController extends HttpServlet
{
    RoomService roomService = new RoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //do get으로 기존의 데이터를 읽어옴.

        //1.사용자 입력값 처리
        long id = Long.parseLong(req.getParameter("id"));
        System.out.println("RoomUpdateController : "+id);

        //2.업무로직
        RoomVo room = roomService.findById(id);
        req.setAttribute("room",room); //단일객체 이므로 room
        System.out.println(room);
        //3.forwarding
        req.getRequestDispatcher("/WEB-INF/views/room/roomUpdate.jsp").forward(req,resp);

    }

    //수정할 값을 post방식을 통해 update한다.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("객실 수정 do post...");
        RoomVo room = new RoomVo();
        //                new File("C:\\Workspaces\\semi_petking\\target\\semi_petking\\upload\\room");
        //new File("C:\\Workspaces\\semi_petking\\src\\main\\webapp\\upload\\room");
        File repository = new File("C:\\Users\\min_j\\Dropbox\\Workspaces\\semi_petking\\target\\semi_petking\\upload\\room");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(repository);
        factory.setSizeThreshold(10 * 1024 * 1024); // 10mb
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        try {
            // 1. 사용자입력값 처리
            List<FileItem> fileItemList = servletFileUpload.parseRequest(req);
            for (FileItem fileItem : fileItemList) {
                String name = fileItem.getFieldName();
                if (fileItem.isFormField()) {
                    // form field
                    String value = fileItem.getString("utf-8");
                    System.out.println("value.............:"+value);
                    room.setValue(name, value);
                } else {
                    // file
                    if (fileItem.getSize() > 0) {
                        String originalFilename = fileItem.getName();
                        int dotIndex = originalFilename.lastIndexOf(".");
                        String ext = dotIndex > -1 ? originalFilename.substring(dotIndex) : "";
                        String renamedFilename = UUID.randomUUID() + ext;
                        RoomAttach attach = new RoomAttach();
                        attach.setRoomAttachOriginalName(originalFilename);
                        attach.setRoomAttachRenamedName(renamedFilename);
                        room.addAttachment(attach);
                        room.setRoomRenamedImg(renamedFilename);
                        File upFile = new File(repository, renamedFilename);
                        fileItem.write(upFile); // 파일 출력
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(room);

        // 2. 업무로직
        int result = roomService.updateRoom(room);

        req.getSession().setAttribute("msg", "객실정보를 성공적으로 수정했습니다.😁");
        // 3. redirect
        //수정에 성공하였다면 룸 상세페이지가 아닌 /camp/campRoomList?id=4로 이동해야 한다..
        // 기존
        // resp.sendRedirect(req.getContextPath() + "/room/roomDetail?id=" + room.getId());
        //신규
        resp.sendRedirect(req.getContextPath() + "/camp/campRoomList?id=" + room.getCampId());

    }
}