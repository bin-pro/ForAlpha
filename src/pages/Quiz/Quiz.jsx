import React from "react";
import { NavBar } from "../../components/NavBar";
import { TabBarItem } from "../../components/TabBarItem";
import { ButtonPrimary } from "../../components/ButtonPrimary";
import { Icon7 } from "../../icons/Icon7";
import { Icon8 } from "../../icons/Icon8";
import { Icon9 } from "../../icons/Icon9";
import { Icon10 } from "../../icons/Icon10";
import { LeftButton } from "../../icons/LeftButton";
import "./style.css";

export const Quiz = () => {
  return (
    <div className="quiz">
    <div className="div-2">
      <div className="overlap-group">
        <div className="feed">
          <div className="products">
            <div className="vertical-card-wrapper">
              <div className="vertical-card">
                <div className="frame" />
                <div className="content">
                  <div className="title">
                    <p className="subtitle">
                      전월세뿐 아니라 매매 시세까지 파악하는 것은 전세 사기를 방지하는 방법이다.
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="products-wrapper">
            <div className="products-2">
              <ButtonPrimary
                className="button-primary-instance"
                divClassName="design-component-instance-node"
                text="O"
              />
              <ButtonPrimary
                className="button-primary-instance"
                divClassName="design-component-instance-node"
                text="X"
              />
            </div>
          </div>
        </div>
        <div className="tab-bar">
                <TabBarItem className="tab-3" icon={<Icon7 className="icon-2" />} selected={false} title="Home" />
                <TabBarItem className="tab-3" icon={<Icon8 className="icon-2" />} selected={false} title="Point" />
                <TabBarItem className="tab-3" icon={<Icon9 className="icon-2" />} selected={false} title="Feed" />
                <TabBarItem className="tab-3" icon={<Icon10 className="icon-2" />} selected={false} title="Profile" />
            </div>
        </div>
      <NavBar
        className="nav-bar-instance"
        hasRightButton={false}
        icon={<LeftButton className="left-button-4" />}
        leftControl="icon"
        pageTitle="OX퀴즈"
        rightButtonClassName="nav-bar-2"
        rightControl="icon"
      />
    </div>
  </div>
  );
};