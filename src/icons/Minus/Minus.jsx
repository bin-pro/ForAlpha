import React from "react";

export const Minus = ({ className }) => {
  return (
    <svg
      className={`minus ${className}`}
      fill="none"
      height="10"
      viewBox="0 0 11 10"
      width="11"
      xmlns="http://www.w3.org/2000/svg"
    >
      <mask
        className="mask"
        height="2"
        id="mask0_10_457"
        maskUnits="userSpaceOnUse"
        style={{ maskType: 'alpha' }}
        width="9"
        x="1"
        y="4"
      >
        <path
          className="path"
          clipRule="evenodd"
          d="M9.40527 4.98822C9.40527 5.3334 9.12545 5.61322 8.78027 5.61322H2.11361C1.76843 5.61322 1.48861 5.3334 1.48861 4.98822C1.48861 4.64304 1.76843 4.36322 2.11361 4.36322L8.78027 4.36322C9.12545 4.36322 9.40527 4.64304 9.40527 4.98822Z"
          fill="#124CF5"
          fillRule="evenodd"
        />
      </mask>
      <g className="g" mask="url(#mask0_10_457)">
        <rect className="rect" fill="#124CF5" height="10" width="10" x="0.447266" />
      </g>
    </svg>
  );
};
