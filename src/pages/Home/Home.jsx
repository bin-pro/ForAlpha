import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { ArrowRightWrapper } from "../../components/ArrowRightWrapper";
import { Image } from "../../components/Image";
import { ListItem } from "../../components/ListItem";
import { NavBar } from "../../components/NavBar";
import { NoOfItemsWrapper } from "../../components/NoOfItemsWrapper";
import { Toggle } from "../../components/Toggle";
import { TabBarItem } from "../../components/TabBarItem";
import { Icon10 } from "../../icons/Icon10";
import { Icon7 } from "../../icons/Icon7";
import { Icon8 } from "../../icons/Icon8";
import { Icon9 } from "../../icons/Icon9";
import { Image5 } from "../../icons/Image5";
import { Image6 } from "../../icons/Image6";
import { Image7 } from "../../icons/Image7";
import { Image8 } from "../../icons/Image8";
import { Image9 } from "../../icons/Image9";
import { BiRefresh } from 'react-icons/bi';
import { RightButton6 } from "../../icons/RightButton6";
import { RightButton7 } from "../../icons/RightButton7";
import { StarFilled1 } from "../../icons/StarFilled1";
import axios from 'axios';
import "./style.css";

export const Home = () => {
  const [selectedTab, setSelectedTab] = useState("거래량"); // 초기 탭을 "거래량"으로 설정
  const [data, setData] = useState([]);
  const [refreshing, setRefreshing] = useState(false);

  const fetchData = async (selectedTab) => {
    try {
      let endpoint = selectedTab === "거래량" ? "trading-volumes" : "popular";
      const response = await axios.get("http://test2.shinhan.site:8002/home/"+endpoint);
      const jsonData = response.data;
      setData(jsonData);
    } catch (error) {
      console.error("API 요청 실패:", error);
    }
  };

  useEffect(() => {
    fetchData(selectedTab);
  }, [selectedTab]);

  // 선택한 탭과 데이터를 업데이트하기 위한 콜백 함수
  const handleTabChange = (tab) => {
    setSelectedTab(tab);
    fetchData(tab);
  };

  // 새로고침
  const handleRefresh = () => {
    setRefreshing(true);
    fetchData(selectedTab);
    setRefreshing(false);
  };

    return (
      <div className="home">
        <div className="div-2">

          <div className="cards">
            <Link to="/point-home">
            <ListItem
              className="list-item-instance"
              controls="icon"
              divClassName="design-component-instance-node"
              icon={<RightButton6 className="right-button-6" />}
              showDescription={false}
              title="포인트 쌓으러 가기"
              visuals="none"
            />
            </Link>
          </div>
          <div className="home-banner">
            <div className="text-wrapper-2">실시간 차트</div>
          </div>
          <BiRefresh className="refresh-icon" onClick={handleRefresh}/>
          <div className="home-toggle">
            <Toggle section1Text="거래량" section2Text="인기순" onTabChange={handleTabChange}/>
          </div>


          <div className="horizontal-card">
            <div className="image-wrapper">
              <Image className="image-instance" icon={<Image5 className="icon-instance-node" />} />
            </div>
            <div className="frame-3">
              <div className="content-3">
                <div className="title-3">삼성전자</div>
                <p className="subtitle">
                  <span className="span">66,000원 </span>
                  <span className="text-wrapper-3">-1.0%</span>
                </p>
              </div>
            </div>
          </div>


          {data.map((item) => (
            <div className="horizontal-card" key={item.id}>
              <div className="image-wrapper">
                <Image className="image-instance" icon={<Image5 className="icon-instance-node" />} />
              </div>
              <div className="frame-3">
                <div className="content-3">
                  <div className="title-3">{item.title}</div>
                  <p className="subtitle">
                    <span className="span">{item.price} </span>
                    <span className="text-wrapper-3">{item.change}</span>
                  </p>
                </div>
              </div>
            </div>
          ))}

          <NavBar
            className="nav-bar-instance"
            icon={<RightButton7 className="right-button-7" />}
            leftControl="none"
            pageTitle="Home"
            rightButtonClassName="nav-bar-2"
            rightControl="icon"
            rightLink="/stock-search"
          />


          <div className="tab-bar">
            <TabBarItem className="tab-3" icon={<Link to="/home" className="tab-3"><Icon7 className="icon-2" /></Link>} selected={true} title="Home" />
            <TabBarItem className="tab-3" icon={<Link to="/point-home" className="tab-3"><Icon8 className="icon-2" /></Link>} selected={false} title="Point" />
            <TabBarItem className="tab-3" icon={<Link to="/feed" className="tab-3"><Icon9 className="icon-2" /></Link>} selected={false} title="Feed" />
            <TabBarItem className="tab-3" icon={<Link to="/profile" className="tab-3"><Icon10 className="icon-2" /></Link>} selected={false} title="Profile" />
          </div>

      </div>
    </div>
    );
  };