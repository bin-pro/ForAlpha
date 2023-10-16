import React from "react";
import { ButtonPrimary } from "../../components/ButtonPrimary";
import { Checkbox } from "../../components/Checkbox";
import { TextField } from "../../components/TextField";
import { Divider1 } from "../../icons/Divider1";
import { StarFilled1 } from "../../icons/StarFilled1";
import "./style.css";
import "../../styles/styleguide.css"

export const FindPassword = () => {
  return (
    <div className="signup">
      <div className="login-options-3">
            <div className="div-wrapper">
              <div className="text-wrapper-10">비밀번호 찾기</div>
            </div>
        <div className="div-3">
          <TextField
            className="text-field-instance"
            placeholder="name@email.com"
            state="empty"
            title="이메일 주소"
          />
        </div>
        <ButtonPrimary
          className="button-primary-instance"
          divClassName="button-primary-2"
          text="인증 번호 발송"
        />
        <div className="div-3">
          <TextField className="text-field-instance" showPlaceholder={false} state="empty" title="인증 번호" />
          <TextField
            className="text-field-instance"
            placeholder="Create a passaword"
            state="empty"
            title="새 비밀번호"
          />
          <TextField
            className="text-field-instance"
            placeholder="Confirm password"
            showTitle={false}
            state="empty"
          />
        </div>
        <ButtonPrimary
          className="button-primary-instance"
          divClassName="button-primary-2"
          text="새 비밀번호 저장"
        />
      </div>
    </div>
  );
};