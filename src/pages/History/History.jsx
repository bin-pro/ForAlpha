import React from "react";
import { useState, useEffect } from "react";
import { NavBar } from "../../components/NavBar";
import { NoOfItemsWrapper } from "../../components/NoOfItemsWrapper";
import { TabBarItem } from "../../components/TabBarItem";
import { Toggle } from "../../components/Toggle";
import { Icon11 } from "../../icons/Icon11";
import { Icon8 } from "../../icons/Icon8";
import { Icon9 } from "../../icons/Icon9";
import { Icon14 } from "../../icons/Icon14";
import { LeftButton } from "../../icons/LeftButton";
import { ContentSwitcher } from "../../components/ContentSwitcher";
import axios from "axios";
import "./style.css";

export const History = () => {

  const [selectedTab, setSelectedTab] = useState("예측 내역"); // 초기 탭 "예측 내역"
  const [HistoryData, setHistoryData] = useState([]);
  const [refreshing, setRefreshing] = useState(false);

  const fetchHistory = async (selectedTab, userUuid) => {
    try {
      let endpoint = selectedTab === "예측 내역" ? "prediction" : "quiz";
      const response = await axios.get(`http://localhost:8002/profile/history/${endpoint}?user-uuid=${userUuid}`);
      const historyData = response.data.payload.predictionHistory;
      setHistoryData(historyData);
      console.log("History loaded");
    } catch (error) {
      console.error("API 요청 실패:", error);
    }
  };

  useEffect(() => {
    const userUuid = "846813a8-d894-4956-9d41-ad6d24292e2c";
    fetchHistory(selectedTab, userUuid);
  }, [selectedTab]);

  // 선택한 탭과 데이터를 업데이트하기 위한 콜백 함수
  const handleTabChange = (tab) => {
    setSelectedTab(tab);
    fetchHistory(tab);
  };

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
          <div>
                  <Toggle section1Text="예측 내역" section2Text="OX 퀴즈 내역" onTabChange={handleTabChange}/>
          </div>
        </div>
        <div className="list">
        {selectedTab === "section1" && (
          <div className="list-item">
            {HistoryData.map((item, index) => (
              <div key={index} className="content">
                <div className="content">
                  <p className="title">
                    <span className="span">{item.date} </span>
                    <span className="text-wrapper-2">{item.points}Point</span>
                  </p>
                  <p className="description">
                    <span className="text-wrapper-3">{item.stockName}</span>
                    <span className="text-wrapper-4">로 </span>
                    <span className="text-wrapper-5">{item.profit}%</span>
                    <span className="text-wrapper-4">를 달성했어요.</span>
                  </p>
                </div>
              </div>
            ))}
          </div>
        )}

        {selectedTab === "section2" && (
          <div className="list-item">
            {HistoryData.map((item, index) => (
              <div key={index} className="content">
                <div className="content">
                  <p className="title">
                    <span className="span">{item.date} </span>
                    <span className="text-wrapper-2">{item.points}Point</span>
                  </p>
                  <p className="description">
                    <span className="text-wrapper-3">{item.quiz}</span>
                    <span className="text-wrapper-4">{item.answer ? "⭕️" : "❌"}</span>
                  </p>
                </div>
              </div>
            ))}
          </div>
        )}
        </div>
      </div>
    </div>
  );
};
