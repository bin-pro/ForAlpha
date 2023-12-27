import PropTypes from "prop-types";
import React from "react";
import { Avatar9 } from "../../icons/Avatar9";
import { HeartFilled } from "../../icons/HearFilled";
import { IconComponentNode } from "../../icons/IconComponentNode";
import { ButtonSecondary } from "../ButtonSecondary";
import { Image } from "../Image";
import { Tag } from "../Tag";
import "./style.css";

export const VerticalCard = ({
  showTitle = true,
  showTag = true,
  showSubtitle = true,
  showButton = true,
  subtitle = "Subtitle",
  title = "Title",
  showDescription = true,
  description = "Description. Lorem ipsum dolor sit amet consectetur adipiscing elit, sed do",
  visuals,
  className,
  hasFrame = true,
  divClassName,
}) => {
  return (
    <div className={`vertical-card visuals-${visuals} ${className}`}>
      {hasFrame && (
        <>
          <>
            {["icon", "image"].includes(visuals) && (
              <div className="frame">
                {visuals === "image" && (
                  <div className="overlap-group">
                    <Image className="image-instance" icon={<IconComponentNode className="icon-instance" />} />
                    {showTag && <Tag className="tag-instance" style="focus" text="TAG" />}
                  </div>
                )}

                {visuals === "icon" && <HeartFilled className="heart-filled" />}
              </div>
            )}

            {visuals === "avatar" && <Avatar9 className="avatar" />}
          </>
        </>
      )}

      <div className="content">
        <div className="title">
          {showTitle && <div className="text-wrapper">{title}</div>}

          {showSubtitle && <div className={`subtitle ${divClassName}`}>{subtitle}</div>}
        </div>
        {showDescription && <p className="description">{description}</p>}

        {showButton && <ButtonSecondary className="button-secondary-instance" text="Button" />}
      </div>
    </div>
  );
};

VerticalCard.propTypes = {
  showTitle: PropTypes.bool,
  showTag: PropTypes.bool,
  showSubtitle: PropTypes.bool,
  showButton: PropTypes.bool,
  subtitle: PropTypes.string,
  title: PropTypes.string,
  showDescription: PropTypes.bool,
  description: PropTypes.string,
  visuals: PropTypes.oneOf(["avatar", "image", "icon"]),
  hasFrame: PropTypes.bool,
};
