import PropTypes from "prop-types";
import React from "react";
import "./style.css";

export const Tag = ({
  showText = true,
  showLeftIcon = false,
  showRightIcon = false,
  text = "Tag",
  style,
  className,
}) => {
  return (
    <div className={`tag ${style} ${className}`}>
      {showText && (
        <div className="text">
          <div className="text-2">{text}</div>
        </div>
      )}
    </div>
  );
};

Tag.propTypes = {
  showText: PropTypes.bool,
  showLeftIcon: PropTypes.bool,
  showRightIcon: PropTypes.bool,
  text: PropTypes.string,
  style: PropTypes.oneOf(["focus", "default"]),
};
