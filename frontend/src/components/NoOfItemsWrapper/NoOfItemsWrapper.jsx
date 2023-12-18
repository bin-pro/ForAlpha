import PropTypes from "prop-types";
import React, { useState } from "react";
import { ContentSwitcher } from "../ContentSwitcher";
import "./style.css";

export const NoOfItemsWrapper = ({
  noOfItems,
  className,
  contentSwitcherTitle = "Section 1",
  contentSwitcherSelected = true,
  contentSwitcherSelectedTrueClassName,
  contentSwitcherDivClassName,
  dividerClassName,
  divider = "https://cdn.animaapp.com/projects/6524a15db6c5edc3e26fb475/releases/6524a452226c039374e07ea9/img/divider.svg",
  contentSwitcherTitle1 = "Section 2",
  onSelectSection,
  hasDivider = true,
  visible = true,
}) => {
  const [selectedSection, setSelectedSection] = useState("section1");

  const handleSectionClick = (section) => {
    setSelectedSection(section);
    onSelectSection(section);
  };

  return (
    <div className={`no-of-items-wrapper ${className}`}>
      {noOfItems === "two" && (
        <>
          <ContentSwitcher
            className={`content-switcher-section ${
              selectedSection === "section1" ? "selectedTrue" : ""
            }`}
            divClassName={contentSwitcherDivClassName}
            selected={selectedSection === "section1"}
            title={contentSwitcherTitle}
            onClick={() => handleSectionClick("section1")}
          />
          <ContentSwitcher
            className={`content-switcher-instance ${
              selectedSection === "section2" ? "selectedTrue" : ""
            }`}
            selected={selectedSection === "section2"}
            title={contentSwitcherTitle1}
            onClick={() => handleSectionClick("section2")}
          />
        </>
      )}

      {noOfItems === "three" && (
        <>
          <ContentSwitcher
            className={contentSwitcherSelectedTrueClassName}
            divClassName={contentSwitcherDivClassName}
            selected={contentSwitcherSelected}
            title={contentSwitcherTitle}
            onClick={() => handleSectionClick("section1")}
          />
          <img className={`divider ${dividerClassName}`} alt="Divider" src={divider} />
        </>
      )}

      {noOfItems === "three" && (
        <>
          <>
            {hasDivider && (
              <img
                className="img"
                alt="Divider"
                src="https://cdn.animaapp.com/projects/6524a15db6c5edc3e26fb475/releases/6524a452226c039374e07ea9/img/divider.svg"
              />
            )}
          </>
          <>{visible && <ContentSwitcher className="content-switcher-instance" selected={false} title="Section 3" />}</>
        </>
      )}

      {noOfItems === "four" && (
        <>
          <img
            className="divider"
            alt="Divider"
            src="https://cdn.animaapp.com/projects/6524a15db6c5edc3e26fb475/releases/6524a452226c039374e07ea9/img/divider.svg"
          />
          <ContentSwitcher className="content-switcher-section" selected={false} title="Section 3" />
          <img
            className="img"
            alt="Divider"
            src="https://cdn.animaapp.com/projects/6524a15db6c5edc3e26fb475/releases/6524a452226c039374e07ea9/img/divider.svg"
          />
          <ContentSwitcher className="content-switcher-instance" selected={false} title="Section 4" />
        </>
      )}
    </div>
  );
};

NoOfItemsWrapper.propTypes = {
  noOfItems: PropTypes.oneOf(["two", "three", "four"]),
  contentSwitcherTitle: PropTypes.string,
  contentSwitcherSelected: PropTypes.bool,
  divider: PropTypes.string,
  contentSwitcherTitle1: PropTypes.string,
  hasDivider: PropTypes.bool,
  visible: PropTypes.bool,
  onSelectSection: PropTypes.func,
  contentSwitcherDivClassName: PropTypes.string,
};
