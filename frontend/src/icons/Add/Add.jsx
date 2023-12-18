import React from "react";

export const Add = ({ className }) => {
  return (
    <svg
      className={`add ${className}`}
      fill="none"
      height="10"
      viewBox="0 0 11 10"
      width="11"
      xmlns="http://www.w3.org/2000/svg"
    >
      <mask
        className="mask"
        height="10"
        id="mask0_10_463"
        maskUnits="userSpaceOnUse"
        style={{ maskType: 'alpha' }}
        width="11"
        x="0"
        y="0"
      >
        <path
          className="path"
          d="M6.07194 0.833313C6.07194 0.488135 5.79212 0.208313 5.44694 0.208313C5.10176 0.208313 4.82194 0.488135 4.82194 0.833313V4.37498H1.28027C0.935096 4.37498 0.655273 4.6548 0.655273 4.99998C0.655273 5.34516 0.935096 5.62498 1.28027 5.62498H4.82194V9.16665C4.82194 9.51183 5.10176 9.79165 5.44694 9.79165C5.79212 9.79165 6.07194 9.51183 6.07194 9.16665V5.62498H9.61361C9.95879 5.62498 10.2386 5.34516 10.2386 4.99998C10.2386 4.6548 9.95879 4.37498 9.61361 4.37498H6.07194V0.833313Z"
          fill="#124CF5"
        />
      </mask>
      <g className="g" mask="url(#mask0_10_463)">
        <rect className="rect" fill="#124CF5" height="10" width="10" x="0.447266" />
      </g>
    </svg>
  );
};
