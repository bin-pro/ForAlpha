/*
We're constantly improving the code you see. 
Please share your feedback here: https://form.asana.com/?k=uvp-HPgd3_hyoXRBw1IcNg&d=1152665201300829
*/

import PropTypes from "prop-types";
import React from "react";
import { Avatar9 } from "../../icons/Avatar9";
import { LeftIcon } from "../../icons/LeftIcon";
import { LeftIcon1 } from "../../icons/LeftIcon1";
import { LeftIcon2 } from "../../icons/LeftIcon2";
import { RightButton3 } from "../../icons/RightButton3";
import "./style.css";

export const ListItem = ({
  showTitle = true,
  showDescription = true,
  title = "Title",
  description = "Description. Lorem ipsum dolor sit amet consectetur adipiscing elit, sed do",
  visuals,
  controls,
  className,
  divClassName,
  icon = <RightButton3 className="right-button" />,
}) => {
  return (
    <div className={`list-item ${className}`}>
      {visuals === "icon" && controls === "icon" && (
        <div className="supporting-visuals">
          <LeftIcon className="left-icon" />
        </div>
      )}

      {((controls === "badge" && visuals === "avatar") ||
        (controls === "badge" && visuals === "icon") ||
        (controls === "checkbox" && visuals === "avatar") ||
        (controls === "checkbox" && visuals === "icon") ||
        (controls === "toggle" && visuals === "avatar") ||
        (controls === "toggle" && visuals === "icon")) && (
        <div className={`div visuals-${visuals}`}>
          {controls === "toggle" && visuals === "icon" && <LeftIcon1 className="left-icon" />}

          {visuals === "icon" && controls === "checkbox" && <LeftIcon2 className="left-icon" />}

          {controls === "badge" && visuals === "icon" && <LeftIcon className="left-icon" />}

          {controls === "toggle" && visuals === "avatar" && <Avatar9 className="avatar" />}

          {visuals === "avatar" && controls === "checkbox" && <Avatar9 className="avatar" />}

          {controls === "badge" && visuals === "avatar" && <Avatar9 className="avatar" />}
        </div>
      )}

      {((controls === "badge" && visuals === "avatar") ||
        (controls === "badge" && visuals === "icon") ||
        (controls === "checkbox" && visuals === "avatar") ||
        (controls === "checkbox" && visuals === "icon") ||
        (controls === "icon" && visuals === "icon") ||
        (controls === "icon" && visuals === "none") ||
        (controls === "none" && visuals === "none") ||
        (controls === "toggle" && visuals === "avatar") ||
        (controls === "toggle" && visuals === "icon")) && (
        <div className="content">
          {showTitle && (
            <div className={`title ${visuals === "none" && controls === "icon" ? divClassName : undefined}`}>
              {title}
            </div>
          )}

          {showDescription && <p className="description">{description}</p>}
        </div>
      )}

      {((controls === "badge" && visuals === "avatar") ||
        (controls === "badge" && visuals === "icon") ||
        (controls === "checkbox" && visuals === "avatar") ||
        (controls === "checkbox" && visuals === "icon") ||
        (controls === "toggle" && visuals === "avatar") ||
        (controls === "toggle" && visuals === "icon")) && (
        <div className={`toggle controls-${controls}`}>
          {["badge", "toggle"].includes(controls) && <div className="ellipse">{controls === "badge" && <>9</>}</div>}
        </div>
      )}

      {((controls === "badge" && visuals === "none") ||
        (controls === "checkbox" && visuals === "none") ||
        (controls === "none" && visuals === "avatar") ||
        (controls === "none" && visuals === "icon") ||
        (controls === "toggle" && visuals === "none")) && (
        <>
          <div className={`supporting-visuals-2 ${controls} visuals-0-${visuals}`}>
            {visuals === "icon" && <LeftIcon className="left-icon" />}

            {visuals === "avatar" && <Avatar9 className="avatar" />}

            {visuals === "none" && (
              <>
                <>{showTitle && <div className={`title ${divClassName}`}>{title}</div>}</>
                <>{showDescription && <p className="description">{description}</p>}</>
              </>
            )}
          </div>
          <div className={`content-2 controls-1-${controls}`}>
            {controls === "none" && (
              <>
                <>{showTitle && <div className="title">{title}</div>}</>
                <>{showDescription && <p className="description">{description}</p>}</>
              </>
            )}

            {["badge", "toggle"].includes(controls) && (
              <div className="ellipse-2">{controls === "badge" && <>9</>}</div>
            )}
          </div>
        </>
      )}

      {controls === "icon" && visuals === "avatar" && (
        <>
          <div className="avatar-wrapper">
            <Avatar9 className="avatar" />
          </div>
          <div className="content">
            {showTitle && <div className="title">{title}</div>}

            {showDescription && <p className="description">{description}</p>}
          </div>
          <RightButton3 className="right-button" />
        </>
      )}

      {visuals === "none" && controls === "icon" && <>{icon}</>}

      {visuals === "icon" && controls === "icon" && <RightButton3 className="right-button" />}
    </div>
  );
};

ListItem.propTypes = {
  showTitle: PropTypes.bool,
  showDescription: PropTypes.bool,
  title: PropTypes.string,
  description: PropTypes.string,
  visuals: PropTypes.oneOf(["avatar", "none", "icon"]),
  controls: PropTypes.oneOf(["none", "icon", "checkbox", "badge", "toggle"]),
};
