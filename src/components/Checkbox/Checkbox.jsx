import PropTypes from "prop-types";
import React from "react";
import { useReducer } from "react";
import { Check } from "../../icons/Check";
import { Check1 } from "../../icons/Check1";
import { Check2 } from "../../icons/Check2";
import "./style.css";

export const Checkbox = ({ size, selected, className }) => {
  const [state, dispatch] = useReducer(reducer, {
    size: size || "small",
    selected: selected || false,
  });

  return (
    <div
      className={`checkbox selected-${state.selected} ${state.size} ${className}`}
      onClick={() => {
        dispatch("click");
      }}
    >
      {state.selected && state.size === "large" && <Check className="check-instance" />}

      {state.selected && state.size === "medium" && <Check1 className="check-1" />}

      {state.selected && state.size === "small" && <Check2 className="check-2" />}
    </div>
  );
};

function reducer(state, action) {
  if (state.selected === false && state.size === "large") {
    switch (action) {
      case "click":
        return {
          selected: true,
          size: "large",
        };
    }
  }

  if (state.selected === true && state.size === "large") {
    switch (action) {
      case "click":
        return {
          selected: false,
          size: "large",
        };
    }
  }

  if (state.selected === false && state.size === "medium") {
    switch (action) {
      case "click":
        return {
          selected: true,
          size: "medium",
        };
    }
  }

  if (state.selected === true && state.size === "medium") {
    switch (action) {
      case "click":
        return {
          selected: false,
          size: "medium",
        };
    }
  }

  if (state.selected === false && state.size === "small") {
    switch (action) {
      case "click":
        return {
          selected: true,
          size: "small",
        };
    }
  }

  if (state.selected === true && state.size === "small") {
    switch (action) {
      case "click":
        return {
          selected: false,
          size: "small",
        };
    }
  }

  return state;
}

Checkbox.propTypes = {
  size: PropTypes.oneOf(["large", "medium", "small"]),
  selected: PropTypes.bool,
};
