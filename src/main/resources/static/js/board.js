const boardMessages = [
    '제목을 올바르게 입력하세요',
    '본문내용을 올바르게 입력하세요',
    '자동가입방지를 올바르게 클릭하세요',
];

// 모든 error-message 요소의 내용을 초기화
function clearMessages() {
    document.querySelectorAll(".error-message")
        .forEach(error => error.textContent='');
}

// 오류 메세지 출력
const displayErrorsMessages = (input, message) => {
    let error = document.createElement('div');
    error.className = 'error-message';
    error.textContent = message;
    input.parentElement.appendChild(error);
}

// 로그인 폼 유효성 검사
const validBoard = (form) => {
    let isValid = true;

    //로그인 폼 안의 모든 input 요소 수집
    const inputs = form.querySelectorAll('input:not([readonly]), textarea:not([readonly])');
    inputs.forEach((input, idx) => {//input 요소를 하나씩 검사
        if (!input.checkValidity()) {//html5 태그를 이용한 유효성 검사
            displayErrorsMessages(input, boardMessages[idx]);
            isValid = false;
        }
    });

    const ggrecaptcha = grecaptcha.getResponse();
    if(ggrecaptcha === ""){
        const recaptcha = document.querySelector('#recaptcha');
        displayErrorsMessages(recaptcha, boardMessages[2]);
        isValid = false;
    }
    console.log(ggrecaptcha);

    return isValid;
}


const submitBoardfrm = async (frm) => {
    const formData = new FormData(frm);

    fetch('/board/write', {
        method : 'post',
        body : formData
    }).then(async response => {
        if(response.ok) {// 로그인이 성공했다면
            alert('게시글이 등록되었습니다.');
            location.href = '/board/list';
        }else if(response.status === 400){
            alert(await response.text());
        }else {//로그인에 실패했다면
            alert('게시글 등록에 실패했습니다. 다시 시도해 주세요');
        }
    }).catch(error => {
        console.error('board write error : ', error);
        alert('서버와 통신중 오류가 발생했습니다. 관리자에게 문의하세요.');
    });
}
