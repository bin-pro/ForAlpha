import PropTypes from "prop-types";
import React from "react";
import { Check } from "../../icons/Check";
import { Check1 } from "../../icons/Check1";
import { Check2 } from "../../icons/Check2";
import "./style.css";

export const Checkbox = ({ size, selected, className, onClick }) => {
  return (
    <div
      className={`checkbox selected-${selected} ${size} ${className}`}
      onClick={() => {
        if (onClick) {
          onClick(!selected);
        }
      }}
    >
      {selected && size === "large" && <Check className="check-instance" />}
      {selected && size === "medium" && <Check1 className="check-1" />}
      {selected && size === "small" && <Check2 className="check-2" />}
    </div>
  );
};

Checkbox.propTypes = {
  size: PropTypes.oneOf(["large", "medium", "small"]),
  selected: PropTypes.bool,
};