/*
We're constantly improving the code you see. 
Please share your feedback here: https://form.asana.com/?k=uvp-HPgd3_hyoXRBw1IcNg&d=1152665201300829
*/

import PropTypes from "prop-types";
import React from "react";

export const ShiftOn2 = ({ color = "#202632", className }) => {
  return (
    <svg
      className={`shift-on-2 ${className}`}
      fill="none"
      height="33"
      viewBox="0 0 33 33"
      width="33"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        className="path"
        d="M12.505 17.7058H7.86885C7.4274 17.7058 7.20277 17.1746 7.51013 16.8572L16.5094 7.56451C16.7057 7.3618 17.0306 7.3618 17.2269 7.56451L26.2262 16.8572C26.5336 17.1746 26.3089 17.7058 25.8675 17.7058H21.2307V23.2121C21.2307 23.4884 21.0069 23.7125 20.7309 23.7125H13.0048C12.7288 23.7125 12.505 23.4884 12.505 23.2121V17.7058Z"
        fill={color}
      />
    </svg>
  );
};

ShiftOn2.propTypes = {
  color: PropTypes.string,
};
