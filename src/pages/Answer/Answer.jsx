import React from "react";
import { ButtonPrimary } from "../../components/ButtonPrimary";
import { NavBar } from "../../components/NavBar";
import { TabBarItem } from "../../components/TabBarItem";
import { VerticalCard } from "../../components/VerticalCard";
import { Icon14 } from "../../icons/Icon14";
import { Icon7 } from "../../icons/Icon7";
import { Icon8 } from "../../icons/Icon8";
import { Icon9 } from "../../icons/Icon9";
import { LeftButton } from "../../icons/LeftButton";
import "./style.css";

export const Answer = () => {
  return (
    <div className="answer">
      <div className="div-2">
        <NavBar
            className="nav-bar-instance"
            hasRightButton={false}
            icon={<LeftButton className="left-button-4" />}
            leftControl="icon"
            pageTitle="OX퀴즈"
            rightButtonClassName="nav-bar-2"
            rightControl="icon"
          />
        <div className="overlap-group-2">
          <div className="feed">
            <div className="products">
              <div className="perfect-for-you">
                <div className="vertical-card-wrapper">
                  <VerticalCard
                    className="vertical-card-instance"
                    divClassName="design-component-instance-node"
                    hasFrame={false}
                    showButton={false}
                    showDescription={false}
                    showTitle={false}
                    subtitle="전월세뿐 아니라 매매 시세까지 파악하는 것은 전세 사기를 방지하는 방법이다."
                    visuals="image"
                  />
                </div>
              </div>
            </div>
            <div className="frame-2">
              <div className="frame-wrapper">
                <div className="frame-3">
                  <div className="text-wrapper-2">정답은 ‘O’ 입니다.</div>
                  <p className="p">
                    전셋집을 구하는 세입자 입장에서 가장 중요하게 알아볼 것은 바로 전세로 들어갈 집의 정확한 매매시세를
                    파악하는 것입니다.
                  </p>
                </div>
              </div>
              <ButtonPrimary className="button-primary-instance" divClassName="button-primary-2" text="다음 문제" />
            </div>
          </div>
          <div className="tab-bar">
                <TabBarItem className="tab-3" icon={<Icon7 className="icon-2" />} selected={false} title="Home" />
                <TabBarItem className="tab-3" icon={<Icon8 className="icon-2" />} selected={false} title="Point" />
                <TabBarItem className="tab-3" icon={<Icon9 className="icon-2" />} selected={false} title="Feed" />
                <TabBarItem className="tab-3" icon={<Icon14 className="icon-2" />} selected={false} title="Profile" />
            </div>
          </div>
        </div>
      </div>
  );
};
