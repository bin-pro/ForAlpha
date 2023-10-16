import PropTypes from "prop-types";
import React from "react";
import { ArrowDown2 } from "../../icons/ArrowDown2";
import { Icon9 } from "../../icons/Icon9";
import "./style.css";

export const StateActiveWrapper = ({
  showIcon = true,
  title = "Filter",
  state,
  className,
  icon = <Icon9 className="icon-9" />,
  override = <ArrowDown2 className="arrow-down" />,
}) => {
  return (
    <div className={`state-active-wrapper ${className}`}>
      <div className="title-3">
        {showIcon && <>{icon}</>}

        <div className="title-4">{title}</div>
      </div>
      {override}
    </div>
  );
};

StateActiveWrapper.propTypes = {
  showIcon: PropTypes.bool,
  title: PropTypes.string,
  state: PropTypes.oneOf(["active", "default"]),
};