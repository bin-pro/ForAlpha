import React from "react";
import { Badge } from "../../components/Badge";
import { Image } from "../../components/Image";
import { NavBar } from "../../components/NavBar";
import { TabBarItem } from "../../components/TabBarItem";
import { Icon11 } from "../../icons/Icon11";
import { Icon8 } from "../../icons/Icon8";
import { Icon9 } from "../../icons/Icon9";
import { Icon14 } from "../../icons/Icon14";
import { Image5 } from "../../icons/Image5";
import { LeftButton } from "../../icons/LeftButton";
import "./style.css";

export const Card = () => {
  return (
    <div className="card">
      <div className="div-2">
        <div className="products">
          <div className="card">
            <div className="events">
              <div className="vertical-card">
                <div className="frame">
                  <Image className="image-instance" icon={<Image5 className="icon-instance-node" />} />
                </div>
                <div className="content">
                  <div className="title">
                    <div className="title-2">바이오</div>
                    <div className="subtitle">2023.10.01</div>
                  </div>
                </div>
              </div>
              <Badge className="badge-instance" number="9" type="number" />
            </div>
          </div>
          <div className="card">
            <div className="events">
              <div className="vertical-card">
                <div className="frame">
                  <Image className="image-instance" icon={<Image5 className="icon-instance-node" />} />
                </div>
                <div className="content">
                  <div className="title">
                    <div className="title-2">IT</div>
                    <div className="subtitle">2023.10.01</div>
                  </div>
                </div>
              </div>
              <Badge className="badge-instance" number="5" type="number" />
            </div>
          </div>
        </div>
        <div className="tab-bar">
          <TabBarItem className="tab-3" icon={<Icon11 className="icon-2" />} selected={false} title="Home" />
          <TabBarItem className="tab-3" icon={<Icon8 className="icon-2" />} selected={false} title="Point" />
          <TabBarItem className="tab-3" icon={<Icon9 className="icon-2" />} selected={false} title="Feed" />
          <TabBarItem className="tab-3" icon={<Icon14 className="icon-2" />} selected={false} title="Profile" />
        </div>
        <NavBar
          className="nav-bar-instance"
          icon={<LeftButton className="left-button-5" />}
          leftControl="icon"
          pageTitle="Card"
          rightButtonClassName="nav-bar-2"
          rightControl="none"
        />
      </div>
    </div>
  );
};
