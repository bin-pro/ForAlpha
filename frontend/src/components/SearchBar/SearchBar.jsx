import PropTypes from "prop-types";
import React from "react";
import { Search4 } from "../../icons/Search4";
import "./style.css";

export const SearchBar = ({
  text = "Text",
  placeholder = "Search",
  state,
  className,
  icon = <Search4 className="search-instance" />,
}) => {
  return (
    <div className={`search-bar ${className}`}>
      {icon}
      <div className="text">
        <div className={`text-2 ${state}`}>
          {["filled", "typing"].includes(state) && <>{text}</>}

          {state === "empty" && <>{placeholder}</>}
        </div>
        {state === "typing" && (
          <img
            className="cursor"
            alt="Cursor"
            src="https://cdn.animaapp.com/projects/6524a15db6c5edc3e26fb475/releases/6524a20bd0b8c0b30f5bfa70/img/cursor.svg"
          />
        )}
      </div>
    </div>
  );
};

SearchBar.propTypes = {
  text: PropTypes.string,
  placeholder: PropTypes.string,
  state: PropTypes.oneOf(["typing", "filled", "empty"]),
};
