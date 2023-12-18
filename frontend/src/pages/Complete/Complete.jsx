import React from "react";
import { NavBar } from "../../components/NavBar";
import { TabBarItem } from "../../components/TabBarItem";
import { Icon10 } from "../../icons/Icon10";
import { Icon11 } from "../../icons/Icon11";
import { Icon13 } from "../../icons/Icon13";
import { Icon9 } from "../../icons/Icon9";
import "./style.css";

export const Complete = () => {
  return (
    <div className="complete">
      <div className="div-2">
        <NavBar
          className="nav-bar-instance"
          hasDiv={false}
          hasRightButton={false}
          leftControl="none"
          pageTitle="상승할 주식 예측하기"
          rightButtonClassName="design-component-instance-node"
          rightControl="none"
        />
        <div className="tab-bar">
                <TabBarItem className="tab-3" icon={<Icon11 className="icon-3" />} selected={false} title="Home" />
                <TabBarItem className="tab-bar-item-instance" icon={<Icon13 className="icon-3" />} selected tabNameClassName="tab-2" title="Point"/>
                <TabBarItem className="tab-3" icon={<Icon9 className="icon-3" />} selected={false} title="Feed" />
                <TabBarItem className="tab-3" icon={<Icon10 className="icon-3" />} selected={false} title="Profile" />
        </div>
        <div className="chats">
          <div className="list-item">
            <div className="content">
              <p className="description">
                <span className="text-wrapper">
                  주문 완료!
                  <br />
                </span>
                <span className="span">
                  <br />
                  주문이 완료되었어요
                  <br />
                  결과가 나오면 알려드릴게요
                </span>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
