/*
We're constantly improving the code you see. 
Please share your feedback here: https://form.asana.com/?k=uvp-HPgd3_hyoXRBw1IcNg&d=1152665201300829
*/

import React from "react";

export const LeftButton = ({ className }) => {
  return (
    <svg
      className={`left-button ${className}`}
      fill="none"
      height="20"
      viewBox="0 0 21 20"
      width="21"
      xmlns="http://www.w3.org/2000/svg"
    >
      <mask
        className="mask"
        height="20"
        id="mask0_126_1347"
        maskUnits="userSpaceOnUse"
        style={{ maskType: 'alpha' }}
        width="13"
        x="4"
        y="0"
      >
        <path
          className="path"
          clipRule="evenodd"
          d="M15.7991 0.783094C16.3008 1.27123 16.3008 2.06266 15.7991 2.55079L8.14284 9.99995L15.7991 17.4491C16.3008 17.9372 16.3008 18.7287 15.7991 19.2168C15.2974 19.7049 14.484 19.7049 13.9822 19.2168L4.50916 9.99995L13.9822 0.783094C14.484 0.294958 15.2974 0.294958 15.7991 0.783094Z"
          fill="#124CF5"
          fillRule="evenodd"
        />
      </mask>
      <g className="g" mask="url(#mask0_126_1347)">
        <rect className="rect" fill="#124CF5" height="19.9992" width="19.9992" x="0.342651" y="-0.0012207" />
      </g>
    </svg>
  );
};
