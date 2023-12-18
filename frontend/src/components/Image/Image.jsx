/*
We're constantly improving the code you see. 
Please share your feedback here: https://form.asana.com/?k=uvp-HPgd3_hyoXRBw1IcNg&d=1152665201300829
*/

import React from "react";
import { Image3 } from "../../icons/Image3";
import "./style.css";

export const Image = ({ className, icon = <Image3 className="image-3" /> }) => {
  return <div className={`image ${className}`}>{icon}</div>;
};
