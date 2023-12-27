import PropTypes from "prop-types";
import React from "react";
import { Add } from "../../icons/Add";
import { IconComponentNode } from "../../icons/IconComponentNode";
import { Minus } from "../../icons/Minus";
import { Image } from "../Image";
import { NumberInputField } from "../NumberInputField";
import "./style.css";

export const ShoppingCartItem = ({
  price = "€ 0.00",
  details = "Details",
  showImage = true,
  productName = "Product name",
  className,
  hasQuantityAndPrice = true,
}) => {
  return (
    <div className={`shopping-cart-item ${className}`}>
      {showImage && (
        <Image className="design-component-instance-node" icon={<IconComponentNode className="icon-instance" />} />
      )}

      <div className="content-4">
        <div className="title-and-details">
          <div className="product-name">{productName}</div>
          <div className="details">{details}</div>
        </div>
        {hasQuantityAndPrice && (
          <div className="quantity-and-price">
            <NumberInputField
              className="number-input-field-instance"
              icon={<Minus className="icon-instance-node" />}
              override={<Add className="icon-instance-node" />}
              value="1"
            />
            <div className="element">{price}</div>
          </div>
        )}
      </div>
    </div>
  );
};

ShoppingCartItem.propTypes = {
  price: PropTypes.string,
  details: PropTypes.string,
  showImage: PropTypes.bool,
  productName: PropTypes.string,
  hasQuantityAndPrice: PropTypes.bool,
};
