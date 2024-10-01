
//포인트 사용 검증
export async function fetchPoint(InputValue) {
    const res = await fetch(`/checkPoint?point=${InputValue}`);
    const flag = await res.json();
    return  flag;

}

//결제 검증
export async function fetchPayPoint(InputValue) {
    const res = await fetch(`/payPoint?point=${InputValue}`);
    const message = await res.json();
    return message.message; // 메시지 반환
}
