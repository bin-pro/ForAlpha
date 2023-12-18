/*
We're constantly improving the code you see. 
Please share your feedback here: https://form.asana.com/?k=uvp-HPgd3_hyoXRBw1IcNg&d=1152665201300829
*/

import React from "react";

export const Check = ({ className }) => {
  return (
    <svg
      className={`check ${className}`}
      fill="none"
      height="16"
      viewBox="0 0 17 16"
      width="17"
      xmlns="http://www.w3.org/2000/svg"
    >
      <mask
        className="mask"
        height="13"
        id="mask0_8_500"
        maskUnits="userSpaceOnUse"
        style={{ maskType: 'alpha' }}
        width="17"
        x="0"
        y="2"
      >
        <path
          className="path"
          clipRule="evenodd"
          d="M16.5933 3.06802C17.0355 3.519 17.029 4.24366 16.5787 4.68659L7.05102 14.0584L1.26342 8.36548C0.813123 7.92256 0.806605 7.1979 1.24886 6.74692C1.69112 6.29594 2.41468 6.28941 2.86498 6.73234L7.05102 10.8499L14.9772 3.05344C15.4275 2.61051 16.151 2.61704 16.5933 3.06802Z"
          fill="white"
          fillRule="evenodd"
        />
      </mask>
      <g className="g" mask="url(#mask0_8_500)">
        <rect className="rect" fill="white" height="15.9994" width="15.9994" x="0.921875" y="0.00012207" />
      </g>
    </svg>
  );
};