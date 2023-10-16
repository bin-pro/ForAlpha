import PropTypes from "prop-types";
import React from "react";
import "./style.css";

export const TextField = ({
  placeholder = "Placeholder",
  showIcon = false,
  content = "Text",
  supportText = "Support text",
  showUnit = false,
  unit = "€",
  showTitle = true,
  showPlaceholder = true,
  title = "Title",
  showSupportText = false,
  state,
  className,
  cursor = "https://cdn.animaapp.com/projects/6524a15db6c5edc3e26fb475/releases/6524a20bd0b8c0b30f5bfa70/img/cursor.svg",
  fieldClassName,
  hasText = true,
}) => {
  return (
    <div className={`text-field ${className}`}>
      {showTitle && <div className={`title ${state}`}>{title}</div>}

      <div className={`field state-${state} ${fieldClassName}`}>
        <div className="content">
          {hasText && (
            <div className="text">
              {state === "empty" && (
                <>
                  <>{showPlaceholder && <div className="text-wrapper">{placeholder}</div>}</>
                </>
              )}

              {["error", "filled", "inactive", "typing"].includes(state) && <div className="div">{content}</div>}

              {state === "typing" && <img className="cursor" alt="Cursor" src={cursor} />}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

TextField.propTypes = {
  placeholder: PropTypes.string,
  showIcon: PropTypes.bool,
  content: PropTypes.string,
  supportText: PropTypes.string,
  showUnit: PropTypes.bool,
  unit: PropTypes.string,
  showTitle: PropTypes.bool,
  showPlaceholder: PropTypes.bool,
  title: PropTypes.string,
  showSupportText: PropTypes.bool,
  state: PropTypes.oneOf(["inactive", "filled", "empty", "error", "typing"]),
  cursor: PropTypes.string,
  hasText: PropTypes.bool,
};
