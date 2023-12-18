/*
We're constantly improving the code you see. 
Please share your feedback here: https://form.asana.com/?k=uvp-HPgd3_hyoXRBw1IcNg&d=1152665201300829
*/

import PropTypes from "prop-types";
import React from "react";
import { Icon5 } from "../../icons/Icon5";
import "./style.css";

export const TabBarItem = ({
  title = "Tab 1",
  showTitle = true,
  selected,
  className,
  icon = <Icon5 className="icon-5" />,
  tabNameClassName,
}) => {
  return (
    <div className={`tab-bar-item ${className}`}>
      {icon}

      {showTitle && <div className={`tab-name selected-1-${selected} ${tabNameClassName}`}>{title}</div>}
    </div>
  );
};

TabBarItem.propTypes = {
  title: PropTypes.string,
  showTitle: PropTypes.bool,
  selected: PropTypes.bool,
};
