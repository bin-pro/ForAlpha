import styled, { css } from "styled-components";

export const Wrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 14vw;
`;
export const ToggleBtn = styled.button`
  width: 4vw;
  height: 5vh;
  border-radius: 30px;
  border: none;
  cursor: pointer;
  background-color: ${props => (!props.toggle ? "none" : "#0046FF")};
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: all 0.5s ease-in-out;
`;
export const Circle = styled.div`
  background-color: white;
  width: 1.5vw;
  height: 1.5vw;
  border-radius: 50px;
  position: absolute;
  left: 0.5vw;
  transition: all 0.5s ease-in-out;
  ${props =>
    props.toggle &&
    css`
      transform: translate(1.5vw, 0);
      transition: all 0.5s ease-in-out;
    `}
`;