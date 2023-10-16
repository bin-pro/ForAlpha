import React from "react";
import { NavBar } from "../../components/NavBar";
import { NoOfItemsWrapper } from "../../components/NoOfItemsWrapper";
import { TabBarItem } from "../../components/TabBarItem";
import { Icon11 } from "../../icons/Icon11";
import { Icon8 } from "../../icons/Icon8";
import { Icon9 } from "../../icons/Icon9";
import { Icon14 } from "../../icons/Icon14";
import { LeftButton } from "../../icons/LeftButton";
import "./style.css";

export const History = () => {
  return (
    <div className="history">
      <div className="div-2">
        <NavBar
          className="nav-bar-instance"
          hasRightButton={false}
          icon={<LeftButton className="left-button-6" />}
          leftControl="icon"
          pageTitle="History"
          rightButtonClassName="design-component-instance-node"
          rightControl="icon"
        />
        <div className="tab-bar">
                <TabBarItem className="tab-3" icon={<Icon11 className="icon-2" />} selected={false} title="Home" />
                <TabBarItem className="tab-3" icon={<Icon8 className="icon-2" />} selected={false} title="Point" />
                <TabBarItem className="tab-3" icon={<Icon9 className="icon-2" />} selected={false} title="Feed" />
                <TabBarItem className="tab-3" icon={<Icon14 className="icon-2" />} selected={false} title="Profile" />
        </div>
        <div className="comment">
          <div className="frame">
            <div className="div-wrapper">
              <p className="text-wrapper">편식 없이 골고루 투자하는 ‘균형파&#39;</p>
            </div>
          </div>
          <img
            className="divider-3"
            alt="Divider"
            src="https://cdn.animaapp.com/projects/6524a15db6c5edc3e26fb475/releases/6524a452226c039374e07ea9/img/divider-8.svg"
          />
          <NoOfItemsWrapper
            className="content-switcher-2"
            contentSwitcherDivClassName="content-switcher-4"
            contentSwitcherSelected={false}
            contentSwitcherSelectedTrueClassName="content-switcher-3"
            contentSwitcherSelectedTrueClassNameOverride="content-switcher-6"
            contentSwitcherTitle="예측 내역"
            contentSwitcherTitle1="OX퀴즈 내역"
            divider="https://cdn.animaapp.com/projects/6524a15db6c5edc3e26fb475/releases/6524a452226c039374e07ea9/img/divider-16.svg"
            dividerClassName="content-switcher-5"
            hasDivider={false}
            noOfItems="three"
            visible={false}
          />
        </div>
        <div className="list">
          <div className="list-item">
            <div className="content">
              <p className="title">
                <span className="span">2023/10/08-2023/10/10 </span>
                <span className="text-wrapper-2">+50Point</span>
              </p>
              <p className="description">
                <span className="text-wrapper-3">삼성전자</span>
                <span className="text-wrapper-4">로 </span>
                <span className="text-wrapper-5">+5%</span>
                <span className="text-wrapper-4">를 달성했어요.</span>
              </p>
            </div>
          </div>
          <div className="list-item">
            <div className="content">
              <p className="title">
                <span className="span">2023/10/08-2023/10/10 </span>
                <span className="text-wrapper-2">+50Point</span>
              </p>
              <p className="description">
                <span className="text-wrapper-3">삼성전자</span>
                <span className="text-wrapper-4">로 </span>
                <span className="text-wrapper-5">+5%</span>
                <span className="text-wrapper-4">를 달성했어요.</span>
              </p>
            </div>
          </div>
          <div className="list-item">
            <div className="content">
              <p className="title">
                <span className="span">2023/10/08-2023/10/10 </span>
                <span className="text-wrapper-2">+50Point</span>
              </p>
              <p className="description">
                <span className="text-wrapper-3">삼성전자</span>
                <span className="text-wrapper-4">로 </span>
                <span className="text-wrapper-5">+5%</span>
                <span className="text-wrapper-4">를 달성했어요.</span>
              </p>
            </div>
          </div>
          <div className="list-item">
            <div className="content">
              <p className="title">
                <span className="span">2023/10/08-2023/10/10 </span>
                <span className="text-wrapper-2">+50Point</span>
              </p>
              <p className="description">
                <span className="text-wrapper-3">삼성전자</span>
                <span className="text-wrapper-4">로 </span>
                <span className="text-wrapper-5">+5%</span>
                <span className="text-wrapper-4">를 달성했어요.</span>
              </p>
            </div>
          </div>
          <div className="list-item">
            <div className="content">
              <p className="title">
                <span className="span">2023/10/08-2023/10/10 </span>
                <span className="text-wrapper-2">+50Point</span>
              </p>
              <p className="description">
                <span className="text-wrapper-3">삼성전자</span>
                <span className="text-wrapper-4">로 </span>
                <span className="text-wrapper-5">+5%</span>
                <span className="text-wrapper-4">를 달성했어요.</span>
              </p>
            </div>
          </div>
          <div className="list-item">
            <div className="content">
              <p className="title">
                <span className="span">2023/10/08-2023/10/10 </span>
                <span className="text-wrapper-2">+50Point</span>
              </p>
              <p className="description">
                <span className="text-wrapper-3">삼성전자</span>
                <span className="text-wrapper-4">로 </span>
                <span className="text-wrapper-5">+5%</span>
                <span className="text-wrapper-4">를 달성했어요.</span>
              </p>
            </div>
          </div>
          <div className="list-item">
            <div className="content">
              <p className="title">
                <span className="span">2023/10/08-2023/10/10 </span>
                <span className="text-wrapper-2">+50Point</span>
              </p>
              <p className="description">
                <span className="text-wrapper-3">삼성전자</span>
                <span className="text-wrapper-4">로 </span>
                <span className="text-wrapper-5">+5%</span>
                <span className="text-wrapper-4">를 달성했어요.</span>
              </p>
            </div>
          </div>
          <div className="list-item">
            <div className="content">
              <p className="title">
                <span className="span">2023/10/08-2023/10/10 </span>
                <span className="text-wrapper-2">+50Point</span>
              </p>
              <p className="description">
                <span className="text-wrapper-3">삼성전자</span>
                <span className="text-wrapper-4">로 </span>
                <span className="text-wrapper-5">+5%</span>
                <span className="text-wrapper-4">를 달성했어요.</span>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
