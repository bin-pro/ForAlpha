import { ListItem } from ".";

export default {
  title: "Components/ListItem",
  component: ListItem,
  argTypes: {
    visuals: {
      options: ["avatar", "none", "icon"],
      control: { type: "select" },
    },
    controls: {
      options: ["none", "icon", "checkbox", "badge", "toggle"],
      control: { type: "select" },
    },
  },
};

export const Default = {
  args: {
    showTitle: true,
    showDescription: true,
    title: "Title",
    description: "Description. Lorem ipsum dolor sit amet consectetur adipiscing elit, sed do",
    visuals: "avatar",
    controls: "none",
    className: {},
    divClassName: {},
  },
};