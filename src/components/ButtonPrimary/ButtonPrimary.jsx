/*
We're constantly improving the code you see. 
Please share your feedback here: https://form.asana.com/?k=uvp-HPgd3_hyoXRBw1IcNg&d=1152665201300829
*/

import PropTypes from "prop-types";
import React from "react";
import "./style.css";

export const ButtonPrimary = ({
  showLeftIcon = false,
  showText = true,
  showRightIcon = false,
  text = "Button",
  className,
  divClassName,
}) => {
  return (
    <button className={`button-primary ${className}`}>
      {showText && <div className={`button ${divClassName}`}>{text}</div>}
    </button>
  );
};

ButtonPrimary.propTypes = {
  showLeftIcon: PropTypes.bool,
  showText: PropTypes.bool,
  showRightIcon: PropTypes.bool,
  text: PropTypes.string,
};
