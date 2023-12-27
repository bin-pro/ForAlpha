import PropTypes from "prop-types";
import React from "react";
import "./style.css";

export const ButtonSecondary = ({
  showRightIcon = false,
  showLeftIcon = false,
  text = "Button",
  showText = true,
  className,
}) => {
  return (
    <button className={`button-secondary ${className}`}>{showText && <div className="button-2">{text}</div>}</button>
  );
};

ButtonSecondary.propTypes = {
  showRightIcon: PropTypes.bool,
  showLeftIcon: PropTypes.bool,
  text: PropTypes.string,
  showText: PropTypes.bool,
};
