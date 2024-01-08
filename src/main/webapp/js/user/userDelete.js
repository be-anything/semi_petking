document.getElementById('leaveButton').addEventListener('click', function() {
    var popup = window.open('', '탈퇴 창', 'width=500,height=500');

    var content = `
        <html>
        <head>
            <title>탈퇴 사유 선택</title>
        </head>
        <body>
            <form id="reasonForm">
                <h3>탈퇴 사유를 남겨주시면 서비스 개선에 참고하도록 하겠습니다!</h3>
                <input type="checkbox" id="reason1" name="reason" value="이용 불편함">
                <label for="reason1">맘에 드는 캠핑장이 없어요</label><br>
                <input type="checkbox" id="reason2" name="reason" value="서비스 만족도 낮음">
                <label for="reason2">할인 혜택이 없어요</label><br>
                <input type="checkbox" id="reason3" name="reason" value="다른 서비스 이용 중">
                <label for="reason3">자주 사용하지 않아요</label><br>
                <input type="checkbox" id="reason4" name="reason" value="다른 서비스 이용 중">
                <label for="reason5">홍보성 정보가 너무 많아요</label><br>
                <input type="checkbox" id="reason5" name="reason" value="다른 서비스 이용 중">
                <label for="reason6">개인정보를 삭제하고 싶어요</label><br>
                <input type="checkbox" id="reason6" name="reason" value="기타">
                <label for="reason7">기타(직접입력)</label><br>
                <div id="additionalReason" style="display: none;">
                    <label for="otherReason">기타 : </label><br>
                    <textarea id="otherReason" placeholder="기타사유를 입력해주세요."></textarea><br>
                </div><br>
                <button type="submit">계속하기</button>
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
