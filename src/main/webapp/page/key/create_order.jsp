<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  String message = (String) request.getAttribute("message");
%>

<!DOCTYPE html>

<html>
<head>
  <meta charset="UTF-8">
  <title>Tạo đơn hàng ký số</title>

  ```
  <style>
    * {
      box-sizing: border-box;
    }

    body {
      margin: 0;
      min-height: 100vh;
      padding: 22px;
      font-family: Arial, Helvetica, sans-serif;
      background: linear-gradient(135deg, #d9e7f5, #eef5fb);
      color: #071a38;
    }

    .page {
      width: 100%;
      min-height: calc(100vh - 44px);
    }

    .card {
      width: 100%;
      min-height: calc(100vh - 44px);
      background: rgba(255, 255, 255, 0.78);
      border: 1px solid #c7d5e5;
      border-radius: 12px;
      padding: 28px 34px;
      box-shadow: 0 4px 14px rgba(41, 75, 110, 0.18);
    }

    h2 {
      margin: 0 0 24px 0;
      font-size: 32px;
      color: #071a38;
    }

    .message {
      color: #a52222;
      font-size: 20px;
      margin-bottom: 18px;
    }

    .form-grid {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 18px 28px;
      width: 100%;
    }

    .form-group {
      width: 100%;
    }

    label {
      display: block;
      margin-bottom: 7px;
      font-size: 16px;
      font-weight: bold;
      color: #071a38;
    }

    input {
      width: 100%;
      height: 48px;
      padding: 9px 12px;
      border: 1px solid #9ebbd8;
      border-radius: 7px;
      font-size: 16px;
      background: #ffffff;
      color: #071a38;
      outline: none;
    }

    input:focus {
      border-color: #5b9dd9;
      box-shadow: 0 0 8px rgba(91, 157, 217, 0.45);
    }

    .button-row {
      margin-top: 26px;
    }

    button {
      padding: 10px 18px;
      border: 1px solid #aeb9c6;
      border-radius: 7px;
      background: #f5f8fb;
      color: #071a38;
      font-size: 16px;
      cursor: pointer;
    }

    button:hover {
      background: #e8f1f8;
    }

    @media (max-width: 800px) {
      .form-grid {
        grid-template-columns: 1fr;
      }

      .card {
        padding: 22px;
      }

      h2 {
        font-size: 26px;
      }
    }
  </style>
  ```

</head>
<body>

<div class="page">
  <div class="card">
    <h2>Tạo đơn hàng ký số</h2>

    ```
    <% if (message != null) { %>
    <p class="message"><%= message %></p>
    <% } %>

    <form method="post" action="<%= request.getContextPath() %>/CreateSignedOrderController">
      <div class="form-grid">
        <div class="form-group">
          <label>ID vé</label>
          <input type="number" name="idVe" value="9002">
        </div>

        <div class="form-group">
          <label>Số lượng</label>
          <input type="number" name="soLuong" value="1">
        </div>

        <div class="form-group">
          <label>Đơn giá</label>
          <input type="number" name="donGia" value="1850000">
        </div>

        <div class="form-group">
          <label>Tên người mua</label>
          <input type="text" name="tenNguoiMua" value="Nguyen Van A">
        </div>

        <div class="form-group">
          <label>Email người mua</label>
          <input type="text" name="emailNguoiMua" value="user@gmail.com">
        </div>

        <div class="form-group">
          <label>Số điện thoại</label>
          <input type="text" name="sdtNguoiMua" value="0900000000">
        </div>
      </div>

      <div class="button-row">
        <button type="submit">Tạo đơn hàng và tải file JSON</button>
      </div>
    </form>
  </div>
  ```

</div>

</body>
</html>
