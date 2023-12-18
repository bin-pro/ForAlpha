import PropTypes from "prop-types";
import React from "react";
import "./style.css";

export const Divider = ({
  className,
  divider = "https://cdn.animaapp.com/projects/6524a15db6c5edc3e26fb475/releases/6524a20bd0b8c0b30f5bfa70/img/divider.svg",
}) => {
  return <img className={`divider ${className}`} alt="Divider" src={divider} />;
};

Divider.propTypes = {
  divider: PropTypes.string,
};
