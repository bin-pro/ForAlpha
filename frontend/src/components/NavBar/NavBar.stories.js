import { NavBar } from ".";

export default {
  title: "Components/NavBar",
  component: NavBar,
  argTypes: {
    leftControl: {
      options: ["text", "icon", "none"],
      control: { type: "select" },
    },
    rightControl: {
      options: ["avatar", "text", "icon", "none"],
      control: { type: "select" },
    },
  },
};

export const Default = {
  args: {
    leftText: "Cancel",
    rightText: "Edit",
    pageTitle: "Page Title",
    leftControl: "text",
    rightControl: "avatar",
    className: {},
    rightButtonClassName: {},
  },
};
