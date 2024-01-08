document.getElementById('leaveButton').addEventListener('click', function() {
    var popup = window.open('', '탈퇴 창', 'width=300,height=300');

    var content = `
        <html>
        <head>
            <title>탈퇴 사유 선택</title>
        </head>
        <body>
            <form id="reasonForm">
                <h3>탈퇴 사유를 선택해주세요</h3>
                <input type="checkbox" id="reason1" name="reason" value="이용 불편함">
                <label for="reason1">이용 불편함</label><br>
                <input type="checkbox" id="reason2" name="reason" value="서비스 만족도 낮음">
                <label for="reason2">서비스 만족도 낮음</label><br>
                <input type="checkbox" id="reason3" name="reason" value="다른 서비스 이용 중">
                <label for="reason3">다른 서비스 이용 중</label><br>
                <input type="checkbox" id="reason4" name="reason" value="기타">
                <label for="reason4">기타</label><br>
                <div id="additionalReason" style="display: none;">
                    <label for="otherReason">기타 : </label><br>
                    <textarea id="otherReason" placeholder="기타사유를 입력해주세요."></textarea><br>
                </div><br>
                <button type="submit">확인</button>
            </form>
            <script>
                document.getElementById('reason4').addEventListener('change', function() {
                    var additionalReasonField = document.getElementById('additionalReason');
                    if (this.checked) {
                        additionalReasonField.style.display = 'block';
                    } else {
                        additionalReasonField.style.display = 'none';
                    }
                });

                document.getElementById('reasonForm').addEventListener('submit', function(event) {
                    event.preventDefault();

                    // 사용자가 입력한 탈퇴 사유 가져오기
                    var reasons = [];
                    var checkboxes = document.getElementsByName('reason');
                    var otherReason = document.getElementById('otherReason').value.trim();

                    for (var i = 0; i < checkboxes.length; i++) {
                        if (checkboxes[i].checked) {
                            reasons.push(checkboxes[i].value);
                        }
                    }

                    if (reasons.length === 0) {
                        var additionalReasonField = document.getElementById('additionalReason');
                        if (document.getElementById('reason4').checked && otherReason === '') {
                            additionalReasonField.style.display = 'block';
                            alert('탈퇴 사유를 선택하거나 기타 사유를 입력해주세요.');
                            return;
                        }
                        if (document.getElementById('reason4').checked && otherReason !== '') {
                            reasons.push('기타');
                        } else {
                            alert('탈퇴 사유를 선택해주세요.');
                            return;
                        }
                    }

                    // 서버로 데이터 전송
                    fetch('/withdraw', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(result)
                    })
                    .then(response => response.json())
                    .then(data => {
                        alert(data.message); // DeleteController에서 받은 메시지 표시
                        window.close();
                        window.opener.location.href = 'http://localhost:8080/petking/'; // 메인 페이지로 이동
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
                });
            </script>
        </body>
        </html>
    `;

    popup.document.write(content);
});
