import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { ButtonPrimary } from "../../components/ButtonPrimary";
import { Checkbox } from "../../components/Checkbox";
import { TextField } from "../../components/TextField";
import { Divider1 } from "../../icons/Divider1";
import { StarFilled1 } from "../../icons/StarFilled1";
import "./style.css";
import "../../styles/styleguide.css";
import axios from 'axios';

function Signup2() {
  // 초기값
  const [name, setName] = React.useState("");
  const [password, setPassword] = React.useState("");
  const [passwordConfirm, setPasswordConfirm] = React.useState("");
  const [email, setEmail] = React.useState("");
  

  // 오류메세지 상태 저장
  const [nameMessage, setNameMessage] = React.useState("");
  const [passwordMessage, setPasswordMessage] = React.useState("");
  const [passwordConfirmMessage, setPasswordConfirmMessage] = React.useState("");
  const [emailMessage, setEmailMessage] = React.useState("");

  // 유효성 검사
  const [isName, setIsName] = React.useState(false);
  const [isPassword, setIsPassword] = React.useState(false);
  const [isPasswordConfirm, setIsPasswordConfirm] = React.useState(false);
  const [isEmail, setIsEmail] = React.useState(false);
  const [isAgreed, setIsAgreed] = React.useState(false);

  const navigate = useNavigate();

  const toggleAgreement = () => {
    setIsAgreed(!isAgreed);
  };

  const onChangeName = (e) => {
    const currentName = e.target.value;
    setName(currentName);
    if (currentName.length < 2 || currentName.length > 5) {
      setNameMessage("닉네임은 2글자 이상 5글자 이하로 입력해주세요.");
      setIsName(false);
    } else {
      setNameMessage("사용가능한 닉네임 입니다.");
      setIsName(true);
    }
  };

  const onChangePassword = (e) => {
    const currentPassword = e.target.value;
    setPassword(currentPassword);
    const passwordRegExp =
      /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
    if (!passwordRegExp.test(currentPassword)) {
      setPasswordMessage(
        "숫자,영문자,특수문자 조합으로 8자리 이상 입력해주세요."
      );
      setIsPassword(false);
    } else {
      setPasswordMessage("안전한 비밀번호 입니다.");
      setIsPassword(true);
    }
  };

  const onChangePasswordConfirm = (e) => {
    const currentPasswordConfirm = e.target.value;
    setPasswordConfirm(currentPasswordConfirm);
    if (password !== currentPasswordConfirm) {
      setPasswordConfirmMessage("비밀번호가 일치하지 않습니다.");
      setIsPasswordConfirm(false);
    } else {
      setPasswordConfirmMessage("비밀번호가 일치합니다.");
      setIsPasswordConfirm(true);
    }
  };

  const onChangeEmail = (e) => {
    const currentEmail = e.target.value;
    setEmail(currentEmail);
    const emailRegExp =
      /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;

    if (!emailRegExp.test(currentEmail)) {
      setEmailMessage("이메일의 형식이 올바르지 않습니다.");
      setIsEmail(false);
    } else {
      setEmailMessage("사용 가능한 이메일 입니다.");
      setIsEmail(true);
    }
  };

const SIGNUP_API_URL = 'http://test2.shinhan.site:8001/user-service'; // 실제 엔드포인트 URL로 업데이트하기

const handleSubmit = async (e) => {
  e.preventDefault();
  const isValid = isName && isPassword && isPasswordConfirm && isEmail;

  if (isValid) {
    try {
      const response = await axios.post(SIGNUP_API_URL, {
        email: email,
        nickname: name,
        password: password,
      });

      if (response.status === 201) {
        console.log('회원가입 성공', response.data);
        navigate("/home")
      } else {
        console.error('회원가입 실패', response.data);
      }
    } catch (error) {
      // 네트워크 오류 또는 서버 응답 없음
      console.error('회원가입 오류', error);
    }
  }
};

  return (
    <div className="signup">
      <div className="login-options-3">
        <div className="div-wrapper">
          <div className="text-wrapper-10">회원가입</div>
        </div>
        <div className="div-3">
          <div className="text-field-instance">
            <div className="subtitle">닉네임</div>
            <input className="input-field" type="name" name="name" value={name} placeholder="Create a nickname" onChange={onChangeName} />
            <p className="text-wrapper-11"> {nameMessage} </p>
          </div>
          <div className="text-field-instance">
          <div className="subtitle">이메일</div>
            <input className="input-field" type="email" name="email" value={email} placeholder="name@email.com" onChange={onChangeEmail} />
            <p className="text-wrapper-11"> {emailMessage} </p>
          </div>
          <div className="text-field">
            <input className="input-field" type="password" name="password" value={password} placeholder="Create a passaword" onChange={onChangePassword} />
            <p className="text-wrapper-11"> {passwordMessage} </p>
          </div>
          <div className="text-field-instance">
            <input className="input-field" type="password" name="passwordConfirm" value={passwordConfirm} placeholder="Confirm password" onChange={onChangePasswordConfirm} />
            <p className="text-wrapper-11"> {passwordConfirmMessage} </p>
          </div>
        </div>
        <div className="frame-3">
          <Checkbox className="checkbox-instance" selected={isAgreed} size="medium" />
          <div className="text-wrapper-11">개인정보 약관에 동의합니다.</div>
        </div>
        <button
          type="submit"
          className="blue-btn"
          onClick={handleSubmit}
          disabled={!(isEmail && isName && isPassword && isPasswordConfirm)}>회원가입하기</button>
      </div>
    </div>
  );
};

export default Signup2;