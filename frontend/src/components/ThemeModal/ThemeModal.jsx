import React from "react";
import Modal from "react-modal";
import { customModalStyles } from "./style";

export const ThemeModal = ({ isOpen, onRequestClose, themeName, data }) => {
  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      contentLabel="Popup Message"
      style={customModalStyles}
    >
      <h2>{themeName}</h2>
      <ul>
        {data.map((item, index) => (
          <li key={index}>{item.name}</li>
        ))}
      </ul>
    </Modal>
  );
};