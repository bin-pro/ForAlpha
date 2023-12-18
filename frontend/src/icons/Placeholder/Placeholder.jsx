/*
We're constantly improving the code you see. 
Please share your feedback here: https://form.asana.com/?k=uvp-HPgd3_hyoXRBw1IcNg&d=1152665201300829
*/

import React from "react";

export const Placeholder = ({ className }) => {
  return (
    <svg
      className={`placeholder ${className}`}
      fill="none"
      height="24"
      viewBox="0 0 25 24"
      width="25"
      xmlns="http://www.w3.org/2000/svg"
    >
      <mask
        className="mask"
        height="23"
        id="mask0_6_1802"
        maskUnits="userSpaceOnUse"
        style={{ maskType: 'alpha' }}
        width="23"
        x="1"
        y="0"
      >
        <path
          className="path"
          clipRule="evenodd"
          d="M12.6357 22.9988C18.7109 22.9988 23.6357 18.0739 23.6357 11.9988C23.6357 5.92365 18.7109 0.998779 12.6357 0.998779C6.56061 0.998779 1.63574 5.92365 1.63574 11.9988C1.63574 18.0739 6.56061 22.9988 12.6357 22.9988Z"
          fill="#124CF5"
          fillRule="evenodd"
        />
      </mask>
      <g className="g" mask="url(#mask0_6_1802)">
        <rect className="rect" fill="#124CF5" height="24" width="24" x="0.635742" y="-0.0012207" />
      </g>
    </svg>
  );
};
