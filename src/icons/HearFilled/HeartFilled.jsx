import React from "react";

export const HeartFilled = ({ className }) => {
  return (
    <svg
      className={`heart-filled ${className}`}
      fill="none"
      height="20"
      viewBox="0 0 21 20"
      width="21"
      xmlns="http://www.w3.org/2000/svg"
    >
      <mask
        className="mask"
        height="18"
        id="mask0_177_1803"
        maskUnits="userSpaceOnUse"
        style="mask-type:alpha"
        width="21"
        x="0"
        y="1"
      >
        <path
          className="path"
          d="M6.3881 1.15619C3.40691 1.15619 0.971436 3.55163 0.971436 6.52911C0.971436 7.6456 1.19623 9.45991 2.43549 11.6098C3.67133 13.7538 5.88325 16.1748 9.76956 18.564L9.77365 18.5665C10.0718 18.7479 10.4141 18.8439 10.7631 18.8439C11.1121 18.8439 11.4544 18.7479 11.7526 18.5665L11.7566 18.564C15.643 16.1748 17.8549 13.7538 19.0907 11.6098C20.33 9.45991 20.5548 7.6456 20.5548 6.52911C20.5548 3.55163 18.1193 1.15619 15.1381 1.15619C13.5675 1.15619 12.2387 1.99655 11.3799 2.70836C11.1498 2.89909 10.9431 3.08918 10.7631 3.26615C10.5831 3.08918 10.3764 2.89909 10.1463 2.70836C9.28749 1.99655 7.95869 1.15619 6.3881 1.15619Z"
          fill="#124CF5"
        />
      </mask>
      <g className="g" mask="url(#mask0_177_1803)">
        <rect className="rect" fill="#124CF5" height="20" width="20" x="0.763184" />
      </g>
    </svg>
  );
};
