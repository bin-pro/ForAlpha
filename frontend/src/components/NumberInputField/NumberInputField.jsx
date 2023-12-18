import PropTypes from "prop-types";
import React from "react";
import { Add } from "../../icons/Add";
import { Minus } from "../../icons/Minus";
import "./style.css";

export const NumberInputField = ({
  value = "123",
  className,
  icon = <Minus className="icon-instance-node-2" />,
  override = <Add className="icon-instance-node-2" />,
}) => {
  return (
    <div className={`number-input-field ${className}`}>
      <div className="div-2">{icon}</div>
      <div className="number">
        <div className="element-3">{value}</div>
      </div>
      <div className="div-2">{override}</div>
    </div>
  );
};

NumberInputField.propTypes = {
  value: PropTypes.string,
};
