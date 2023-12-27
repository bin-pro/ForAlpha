import React from "react";
import { Link } from "react-router-dom";
import { ListItem } from "../../components/ListItem";
import { NavBar } from "../../components/NavBar";
import { TabBarItem } from "../../components/TabBarItem";
import { Icon10 } from "../../icons/Icon10";
import { Icon11 } from "../../icons/Icon11";
import { Icon9 } from "../../icons/Icon9";
import { Icon13 } from "../../icons/Icon13";
import "./style.css";

export const PointHome = () => {
  return (
    <div className="point-home">
        <div className="div-3">
            <NavBar
                className="nav-bar-instance"
                hasDiv={false}
                hasRightButton={false}
                leftControl="none"
                pageTitle="포인트를 얻으러 가볼까요?"
                rightButtonClassName="nav-bar-2"
                rightControl="none"
            />
            <div className="banner">
                <img
                className="thumnail"
                alt="Thumnail"
                src="https://cdn.animaapp.com/projects/6524a15db6c5edc3e26fb475/releases/6524a452226c039374e07ea9/img/thumnail.png"
                />
            </div>
            <div className="products">
                <div className="option">
                    <Link to="/prediction">
                    <ListItem
                    className="list-item-instance"
                    controls="icon"
                    description="수익률에 따라 포인트를 받을 수 있어요"
                    divClassName="list-item-2"
                    title="📈 상승할 주식 예측하기"
                    visuals="none"
                    />
                    </Link>
                </div>
                <div className="option">
                    <Link to="/quiz">
                    <ListItem
                    className="list-item-instance"
                    controls="icon"
                    description="퀴즈 풀고 10Point 받아가세요"
                    divClassName="list-item-2"
                    title="🖍️️ OX퀴즈 시작하기"
                    visuals="none"
                    />
                    </Link>
                </div>
            </div>
            <div className="tab-bar">
                <TabBarItem className="tab-3" icon={<Link to="/home"><Icon11 className="icon-3" /></Link>} selected={false} title="Home" />
                <TabBarItem className="tab-bar-item-instance" icon={<Link to="/point-home"><Icon13 className="icon-3" /></Link>} selected={true} tabNameClassName="tab-2" title="Point"/>
                <TabBarItem className="tab-3" icon={<Link to="/feed"><Icon9 className="icon-3" /></Link>} selected={false} title="Feed" />
                <TabBarItem className="tab-3" icon={<Link to="/profile"><Icon10 className="icon-3" /></Link>} selected={false} title="Profile" />
            </div>
        </div>
    </div>
  );
};
