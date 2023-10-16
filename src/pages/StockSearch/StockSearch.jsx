import React , { useState, useEffect }from "react";
import { Divider } from "../../components/Divider";
import { NavBar } from "../../components/NavBar";
import { ListItem } from "../../components/ListItem";
import { TabBarItem } from "../../components/TabBarItem";
import { StateActiveWrapper } from "../../components/StateActivateWrapper";
import { Toggle } from "../../components/Toggle";
import { Tag } from "../../components/Tag";
import { Image } from "../../components/Image";
import { SearchBar } from "../../components/SearchBar";
import { Icon9 } from "../../icons/Icon9";
import { Icon10 } from "../../icons/Icon10";
import { Icon11 } from "../../icons/Icon11";
import { Icon13 } from "../../icons/Icon13";
import { Icon26 } from "../../icons/Icon26";
import { BiSearch} from 'react-icons/bi';
import { Image5 } from "../../icons/Image5";
import { ArrowDown2 } from "../../icons/ArrowDown2";
import { Search4 } from "../../icons/Search4";
import { LeftButton } from "../../icons/LeftButton";
import { RightButton6 } from "../../icons/RightButton6";
import axios from 'axios';
import "./style.css";

export const StockSearch = () => {
    const [selectedTab, setSelectedTab] = useState("section1"); // 초기 탭을 "거래량"으로 설정
    const [data, setData] = useState([]);
    const [isLoaded, setIsLoaded] = useState(false);
    const [stockname, setStockName] = React.useState("");
    const [searchResults, setSearchResults] = useState([]);

    const fetchData = async (selectedTab) => {
        try {
          let endpoint = selectedTab === "종목" ? "stock-name" : "theme-name";
          const response = await axios.get(endpoint);
          const jsonData = response.data;
          setData(jsonData);
          setIsLoaded(true);
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
        setSearchResults([]);
    };

    const handleSearch = (event) => {
        if (event.key === "Enter") {
            fetchData(selectedTab, stockname);
        }
    };

    const fetchResult = async (stockname) => {
        try {
            let endpoint = "엔드포인트";
            // 이후 API 요청을 수행하고 데이터를 setData로 설정합니다.
            // ... (API 요청 및 데이터 설정 부분 추가)
        } catch (error) {
            console.error("API 요청 실패:", error);
        }
    };

  return (
    <div className="stock-search">
        <div className="frame-6">
            <NavBar
                className="nav-bar-instance"
                leftControl="icon"
                override={<LeftButton className="left-button-2" />}
                pageTitle="종목검색"
                rightButtonClassNameOverride="nav-bar-5"
                rightControl="none"
            />
            <div className="frame-5">
                {selectedTab === "section1" && (
                    <div className="text-field-instance">
                        <input className="input-field-stock"
                            type="text" name="stockname"
                            value={stockname}
                            placeholder="search"
                            onKeyPress={handleSearch}
                            onChange={(e) => setStockName(e.target.value)} />
                        <BiSearch className="searchbar-icon" />
                    </div>
                    )}
                <div>
                    <Toggle section1Text="종목" section2Text="테마" onTabChange={handleTabChange} />
                </div>
                
                <div className="chips-wrapper">
                    {selectedTab === "section2" && (
                        <div className="text-field-instance">
                            <div className="chips">
                            <ListItem
                                className="list-item-instance"
                                controls="icon"
                                divClassName="design-component-instance-node"
                                icon={<RightButton6 className="right-button-6" />}
                                showDescription={false}
                                title="🖥️ 빅테크"
                                visuals="none"
                            />
                            <ListItem
                                className="list-item-instance"
                                controls="icon"
                                divClassName="design-component-instance-node"
                                icon={<RightButton6 className="right-button-6" />}
                                showDescription={false}
                                title="🏦 금융"
                                visuals="none"
                            />
                            <ListItem
                                className="list-item-instance"
                                controls="icon"
                                divClassName="design-component-instance-node"
                                icon={<RightButton6 className="right-button-6" />}
                                showDescription={false}
                                title="🏦 금융"
                                visuals="none"
                            />
                            <ListItem
                                className="list-item-instance"
                                controls="icon"
                                divClassName="design-component-instance-node"
                                icon={<RightButton6 className="right-button-6" />}
                                showDescription={false}
                                title="🏦 금융"
                                visuals="none"
                            />
                            <ListItem
                                className="list-item-instance"
                                controls="icon"
                                divClassName="design-component-instance-node"
                                icon={<RightButton6 className="right-button-6" />}
                                showDescription={false}
                                title="🏦 금융"
                                visuals="none"
                            />
                            <ListItem
                                className="list-item-instance"
                                controls="icon"
                                divClassName="design-component-instance-node"
                                icon={<RightButton6 className="right-button-6" />}
                                showDescription={false}
                                title="🏦 금융"
                                visuals="none"
                            />
                            <ListItem
                                className="list-item-instance"
                                controls="icon"
                                divClassName="design-component-instance-node"
                                icon={<RightButton6 className="right-button-6" />}
                                showDescription={false}
                                title="🏦 금융"
                                visuals="none"
                            />
                            <ListItem
                                className="list-item-instance"
                                controls="icon"
                                divClassName="design-component-instance-node"
                                icon={<RightButton6 className="right-button-6" />}
                                showDescription={false}
                                title="🏦 금융"
                                visuals="none"
                            />
                            <ListItem
                                className="list-item-instance"
                                controls="icon"
                                divClassName="design-component-instance-node"
                                icon={<RightButton6 className="right-button-6" />}
                                showDescription={false}
                                title="🏦 금융"
                                visuals="none"
                            />
                            <ListItem
                                className="list-item-instance"
                                controls="icon"
                                divClassName="design-component-instance-node"
                                icon={<RightButton6 className="right-button-6" />}
                                showDescription={false}
                                title="🏦 금융"
                                visuals="none"
                            />
                            </div>
                        </div>
                        )}
                </div>
                {isLoaded ? (
                        <div className="data-display">
                            {data.map((item, index) => (
                                <div key={index} className="data-item">
                                    <Image className="image-2" icon={<Image5 className="image-4" />} />
                                    <div className="content-7">
                                        <div className="div-4">
                                            <div className="product-name-2">{item.name}</div>
                                            <p className="details-2">
                                                <span className="text-wrapper-8">{item.price}</span>
                                                <span className="text-wrapper-9">{item.change}%</span>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                    ) : (
                        <p>Loading...</p>
                    )}
                <Divider className="divider-7" />
            </div>
            <div className="tab-bar">
                <TabBarItem className="tab-3" icon={<Icon11 className="icon-3" />} selected={false} title="Home" />
                <TabBarItem className="tab-bar-item-instance" icon={<Icon13 className="icon-3" />} selected tabNameClassName="tab-2" title="Point"/>
                <TabBarItem className="tab-3" icon={<Icon9 className="icon-3" />} selected={false} title="Feed" />
                <TabBarItem className="tab-3" icon={<Icon10 className="icon-3" />} selected={false} title="Profile" />
            </div>
            </div>
    </div>
  );
};