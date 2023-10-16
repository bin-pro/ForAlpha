import PropTypes from "prop-types";
import React from "react";
import { Icon11 } from "../../icons/Icon11";
import "./style.css";

export const Badge = ({ number = "9", type, className }) => {
  return (
    <div className={`badge type-${type} ${className}`}>
      {type === "number" && <div className="element">{number}</div>}

      {type === "icon" && <Icon11 className="icon-11" />}
    </div>
  );
};

Badge.propTypes = {
  number: PropTypes.string,
  type: PropTypes.oneOf(["icon", "empty", "number"]),
};
