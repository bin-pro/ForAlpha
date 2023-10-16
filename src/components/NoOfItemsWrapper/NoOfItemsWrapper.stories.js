import { NoOfItemsWrapper } from ".";

export default {
  title: "Components/NoOfItemsWrapper",
  component: NoOfItemsWrapper,
  argTypes: {
    noOfItems: {
      options: ["two", "three", "four"],
      control: { type: "select" },
    },
  },
};

export const Default = {
  args: {
    noOfItems: "two",
    className: {},
    contentSwitcherTitle: "Section 1",
    contentSwitcherSelected: true,
    contentSwitcherSelectedTrueClassName: {},
    contentSwitcherDivClassName: {},
    dividerClassName: {},
    divider:
      "https://cdn.animaapp.com/projects/6524a15db6c5edc3e26fb475/releases/6524a452226c039374e07ea9/img/divider.svg",
    contentSwitcherTitle1: "Section 2",
    contentSwitcherSelectedTrueClassNameOverride: {},
    hasDivider: true,
    visible: true,
  },
};
