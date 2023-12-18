import React from "react";

export const ArrowDown2 = ({ className }) => {
  return (
    <svg
      className={`arrow-down-2 ${className}`}
      fill="none"
      height="10"
      viewBox="0 0 10 10"
      width="10"
      xmlns="http://www.w3.org/2000/svg"
    >
      <mask
        className="mask"
        height="6"
        id="mask0_10_325"
        maskUnits="userSpaceOnUse"
        style={{ maskType: 'alpha' }}
        width="10"
        x="0"
        y="2"
      >
        <path
          className="path"
          clipRule="evenodd"
          d="M9.60842 2.27157C9.36435 2.02071 8.96864 2.02071 8.72457 2.27157L4.99999 6.09976L1.27542 2.27157C1.03135 2.02071 0.635634 2.02071 0.391566 2.27157C0.147498 2.52243 0.147498 2.92915 0.391566 3.18001L4.99999 7.91663L9.60842 3.18001C9.85249 2.92915 9.85249 2.52243 9.60842 2.27157Z"
          fill="#124CF5"
          fillRule="evenodd"
        />
      </mask>
      <g className="g" mask="url(#mask0_10_325)">
        <rect className="rect" fill="#C5C6CC" height="9.99977" width="9.99961" x="0.000213623" y="-0.000671387" />
      </g>
    </svg>
  );
};
