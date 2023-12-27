import PropTypes from "prop-types";
import React from "react";
import "./style.css";

export const ContentSwitcher = ({ title = "Section", selected, className, divClassName, onClick }) => {
  const handleClick = () => {
    if (onClick) {
      onClick();
    }
  };

  return (
    <div className={`content-switcher selected-${selected} ${className}`}>
      <div className={`section ${divClassName}`}>{title}</div>
    </div>
  );
};

ContentSwitcher.propTypes = {
  title: PropTypes.string,
  selected: PropTypes.bool,
  onClick: PropTypes.func,
};
