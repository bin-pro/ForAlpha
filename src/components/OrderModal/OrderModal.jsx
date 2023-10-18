import React, { useState } from 'react';
import Swal from 'sweetalert2';
import Modal from "react-modal";
import { customModalStyles } from "./style";

const OrderModal = ({ stockname }) => {
  const [orderPoints, setOrderPoints] = useState('');

  const handleOrderClick = () => {
    Swal.fire({
      title: '주문하기',
      html: `
        <p>주문할 주식: ${stockname}</p>
        <label for="orderPoints">주문 포인트</label>
        <input id="orderPoints" type="text" placeholder="투자할 포인트를 입력해주세요" class="swal2-input" 
        value="${orderPoints}" 
        oninput="this.value = this.value.replace(/[^0-9]/g, '')" 
        onchange="this.value = this.value.replace(/[^0-9]/g, '')">
      `,
      showCancelButton: true,
      confirmButtonText: '주문',
      cancelButtonText: '취소',
      preConfirm: () => {
        // 주문 버튼 클릭 시 처리할 로직을 추가할 수 있습니다.
        // 여기에서 orderPoints의 값을 가져와 사용하면 됩니다.
      },
    }).then((result) => {
      if (result.isConfirmed) {
        // 주문 버튼이 클릭되면 여기에서 주문 처리 로직을 수행할 수 있습니다.
        const confirmedOrderPoints = orderPoints;
        // 서버로 데이터를 전달할 수 있습니다.
        const orderData = {
          stock_code: 'string', // 주문할 주식의 코드
          user_id: 'string', // 사용자 ID
          investment_period: 'string', // 투자 기간
          investment_point: confirmedOrderPoints, // 주문 포인트
        };
        
        // 여기에서 orderData를 서버로 전달하는 코드를 추가하세요.
        // Axios, fetch 등을 사용하여 서버 API로 데이터를 전송할 수 있습니다.
        
        // 주문이 완료되었다는 알림을 표시할 수 있습니다.
        Swal.fire('주문 완료', '주문이 정상적으로 처리되었습니다!', 'success');
      }
    });
  };

  return (
    <div>
      <button onClick={handleOrderClick}>주문하기</button>
    </div>
  );
};