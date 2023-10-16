import React, { useState } from "react";
import { ListItem } from "../../components/ListItem";
import { NavBar } from "../../components/NavBar";
import { SearchBar } from "../../components/SearchBar";
import { TabBarItem } from "../../components/TabBarItem";
import { Avatar9 } from "../../icons/Avatar9";
import { Icon12 } from "../../icons/Icon12";
import { Icon11 } from "../../icons/Icon11";
import { Icon10 } from "../../icons/Icon10";
import { Icon8 } from "../../icons/Icon8";
import { Icon9 } from "../../icons/Icon9";
import { Icon7 } from "../../icons/Icon7";
import { BiSearch} from 'react-icons/bi';
import { RightButton3 } from "../../icons/RightButton3";
import { RightButton6 } from "../../icons/RightButton6";
import { RightButton7 } from "../../icons/RightButton7";
import { Search4 } from "../../icons/Search4";
import axios from "axios";
import "./style.css";



export const Friends = () => {
    const [friendname, setFriendName] = useState("");
    const [searchResults, setSearchResults] = useState([]);

  const handleSearch = async () => {
    try {
      const response = await axios.get(`/search?name=${friendname}`);

      if (response.ok) {
        const data = await response.json();
        setSearchResults(data);
      } else {
        console.error("검색 요청이 실패했습니다.");
      }
    } catch (error) {
      console.error("오류 발생:", error);
    }
  };

  return (
    <div className="searchFriends">
        <div className="search-friends">
            <NavBar
                className="nav-bar-instance"
                divClassName="nav-bar-3"
                leftControl="none"
                pageTitle="친구 찾기"
                rightControl="none"
             />
            <div className="text-field-instance">
                <input className="input-field-stock"
                    type="text"
                    name="friendname"
                    value={friendname}
                    onChange={(e) => setFriendName(e.target.value)}
                    onKeyPress={(e) => {
                    if (e.key === "Enter") {
                        handleSearch();
                    }
                    }}
                    placeholder="닉네임" />
                <BiSearch className="searchbar-icon" onClick={handleSearch} />
            </div>
            <div className="friends">
                {searchResults.map((result, index) => (
                <ListItem
                key={index}
                className="list-item-5"
                controls="icon"
                divClassName="list-item-4"
                icon={<Avatar9 className="avatar-15" />}
                override={<RightButton7 className="right-button-6" />}
                showDescription={false}
                title={result.nickname}
                visuals="avatar"
                />
            ))}
            </div>
            <div className="tab-bar">
              <TabBarItem className="tab-3" icon={<Icon11 className="icon-2" />} selected={false} title="Home" />
              <TabBarItem className="tab-3" icon={<Icon8 className="icon-2" />} selected={false} title="Point" />
              <TabBarItem className="tab-3" icon={<Icon12 className="icon-2" />} selected={false} title="Feed" />
              <TabBarItem className="tab-3" icon={<Icon10 className="icon-2" />} selected={false} title="Profile" />
            </div>
        </div>
    </div>
  )
};
