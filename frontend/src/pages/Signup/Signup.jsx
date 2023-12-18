import React from "react";
import { ButtonPrimary } from "../../components/ButtonPrimary";
import { Checkbox } from "../../components/Checkbox";
import { TextField } from "../../components/TextField";
import { Divider1 } from "../../icons/Divider1";
import { StarFilled1 } from "../../icons/StarFilled1";
import "./style.css";
import "../../styles/styleguide.css"

export const Signup = () => {
  return (
    <div className="signup">
          <div className="login-options-3">
            <div className="div-wrapper">
              <div className="text-wrapper-10">회원가입</div>
            </div>
            <div className="div-3">
              <TextField
                className="text-field-instance"
                fieldClassName="text-field-2"
                hasText={false}
                state="typing"
                title="닉네임"
              />
              <TextField
                className="text-field-instance"
                placeholder="name@email.com"
                state="empty"
                title="이메일 주소"
              />
              <TextField
                className="text-field-instance"
                placeholder="Create a passaword"
                state="empty"
                title="비밀번호"
              />
              <TextField
                className="text-field-instance"
                placeholder="Confirm password"
                showTitle={false}
                state="empty"
              />
            </div>
            <div className="frame-3">
              <Checkbox className="checkbox-instance" selected={false} size="medium" />
              <div className="text-wrapper-11">개인정보 약관에 동의합니다.</div>
            </div>
            <ButtonPrimary className="button-primary-instance" divClassName="button-primary-2" text="회원가입" />
          </div>
        </div>
  );
};