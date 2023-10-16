import PropTypes from "prop-types";
import React from "react";
import { Link } from "react-router-dom";
import { Avatar9 } from "../../icons/Avatar9";
import { LeftButton } from "../../icons/LeftButton";
import { RightButton3 } from "../../icons/RightButton3";
import "./style.css";

export const NavBar = ({
  leftText = "Cancel",
  rightText = "Edit",
  pageTitle = "Page Title",
  leftControl,
  rightControl,
  className,
  icon = <RightButton3 className="right-button-2" />,
  rightButtonClassName,
  leftLink,
  rightLink,
}) => {
  return (
    <div className={`nav-bar ${className}`}>
      {rightControl === "icon" && leftControl === "icon" && <RightButton3 className="right-button-2" />}

      {(rightControl === "avatar" ||
        rightControl === "none" ||
        rightControl === "text" ||
        (leftControl === "icon" && rightControl === "icon")) && (
        <div className={`page-title right-control-${rightControl} left-control-${leftControl}`}>
          {((leftControl === "icon" && rightControl === "avatar") ||
            (leftControl === "icon" && rightControl === "none") ||
            (leftControl === "none" && rightControl === "avatar") ||
            (leftControl === "none" && rightControl === "none") ||
            leftControl === "text" ||
            rightControl === "icon") && <>{pageTitle}</>}

          {rightControl === "text" && ["icon", "none"].includes(leftControl) && <>{rightText}</>}
        </div>
      )}

      {rightControl === "icon" && leftControl === "icon" && <LeftButton className="left-button" />}

      {rightControl === "avatar" && leftControl === "icon" && <LeftButton className="left-button" />}

      {rightControl === "avatar" && ["icon", "none"].includes(leftControl) && <Avatar9 className="avatar-1" />}

      {rightControl === "none" && leftControl === "icon" && <LeftButton className="left-button" />}

      {leftControl === "text" && ["text", "avatar"].includes(rightControl) && (
        <button className="text-wrapper">{leftText}</button>
      )}

      {rightControl === "text" && ["icon", "text"].includes(leftControl) && (
        <div className={`page-title-2 left-control-0-${leftControl}`}>
          {leftControl === "icon" && <>{pageTitle}</>}

          {leftControl === "text" && <>{rightText}</>}
        </div>
      )}

      {rightControl === "icon" && leftControl === "text" && (
        <>
          <Link to={rightLink}>
          <RightButton3 className="right-button-2" />
          </Link>
          <div className="page-title-3">{pageTitle}</div>
          <button className="text-wrapper">{leftText}</button>
        </>
      )}

      {rightControl === "avatar" && leftControl === "text" && <Avatar9 className="avatar-1" />}

      {["none", "text"].includes(leftControl) &&
        (leftControl === "none" || rightControl === "none") &&
        (leftControl === "text" || rightControl === "text") &&
        ["none", "text"].includes(rightControl) && (
          <div className={`right-button-3 right-control-0-${rightControl} ${rightButtonClassName}`}>
            {leftControl === "text" && <>{leftText}</>}

            {rightControl === "text" && <>{pageTitle}</>}
          </div>
        )}

      {leftControl === "none" && rightControl === "icon" && (
        <>
         <Link to={rightLink}>{icon}</Link>
          <div className={`page-title-3 ${rightButtonClassName}`}>{pageTitle}</div>
        </>
      )}

      {rightControl === "text" && leftControl === "icon" && <LeftButton className="left-button" />}
    </div>
  );
};

NavBar.propTypes = {
  leftText: PropTypes.string,
  rightText: PropTypes.string,
  pageTitle: PropTypes.string,
  leftControl: PropTypes.oneOf(["text", "icon", "none"]),
  rightControl: PropTypes.oneOf(["avatar", "text", "icon", "none"]),
};
