<%@ page import="com.edu.hcmuaf.fit.webbanvemaybay.models.Key.UserKey" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  UserKey activeKey = (UserKey) request.getAttribute("activeKey");
  Integer idUser = (Integer) request.getAttribute("idUser");


  String message = (String) session.getAttribute("message");
  if (message != null) {
  session.removeAttribute("message");
  }

  String status = "";
  String statusClass = "badge-default";

  if (activeKey != null && activeKey.getStatus() != null) {
  status = activeKey.getStatus();

  if ("ACTIVE".equalsIgnoreCase(status)) {
  statusClass = "badge-active";
  } else if ("LOST".equalsIgnoreCase(status)) {
  statusClass = "badge-lost";
  } else if ("REVOKED".equalsIgnoreCase(status)) {
  statusClass = "badge-revoked";
  }
  }


%>

<!DOCTYPE html>

<html>
<head>
  <meta charset="UTF-8">
  <title>Quản lý khóa</title>


  <style>
    * {
      box-sizing: border-box;
    }

    body {
      margin: 0;
      padding: 18px;
      font-family: Arial, Helvetica, sans-serif;
      color: #071a38;
      background: linear-gradient(135deg, #d9e7f5, #eef5fb);
    }

    .page-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 8px;
    }

    h2 {
      margin: 0;
      font-size: 24px;
      color: #071a38;
    }

    h3 {
      margin: 0 0 10px 0;
      font-size: 18px;
      color: #071a38;
    }

    .user-info {
      display: flex;
      align-items: center;
      gap: 7px;
      font-size: 13px;
      color: #071a38;
    }

    .user-icon {
      width: 28px;
      height: 28px;
      border-radius: 50%;
      background: #b8cce2;
      display: inline-flex;
      align-items: center;
      justify-content: center;
      font-size: 16px;
    }

    .card {
      background: rgba(255, 255, 255, 0.75);
      border: 1px solid #c7d5e5;
      border-radius: 9px;
      padding: 12px 14px;
      margin-bottom: 14px;
      box-shadow: 0 4px 10px rgba(41, 75, 110, 0.18);
    }

    .message {
      background: #e9f8ef;
      border: 1px solid #91d6a5;
      color: #146b2e;
      padding: 9px 12px;
      border-radius: 7px;
      margin-bottom: 12px;
      font-size: 14px;
    }

    .empty {
      margin: 6px 0 0 0;
      color: #333;
      font-size: 14px;
    }

    .key-row {
      display: grid;
      grid-template-columns: 140px 220px 1fr;
      gap: 20px;
      align-items: start;
    }

    .field {
      font-size: 13px;
      line-height: 1.8;
    }

    .field b {
      font-weight: 600;
    }

    .badge {
      display: inline-block;
      padding: 3px 9px;
      border-radius: 999px;
      font-size: 12px;
      font-weight: 600;
    }

    .badge-active {
      background: #bcecc5;
      color: #13812b;
    }

    .badge-lost {
      background: #ffe0b2;
      color: #a75c00;
    }

    .badge-revoked {
      background: #ffd0d0;
      color: #a52222;
    }

    .badge-default {
      background: #e3e6ea;
      color: #333;
    }

    textarea {
      width: 100%;
      border: 1px solid #9ebbd8;
      border-radius: 7px;
      padding: 8px;
      font-family: Consolas, "Courier New", monospace;
      font-size: 12px;
      color: #10233d;
      resize: vertical;
      outline: none;
      background-color: #f8fbff;
    }

    textarea:focus {
      border-color: #5b9dd9;
      box-shadow: 0 0 8px rgba(91, 157, 217, 0.5);
    }

    .public-key-box {
      min-height: 58px;
      background-image:
              linear-gradient(#d8e6f3 1px, transparent 1px),
              linear-gradient(90deg, #d8e6f3 1px, transparent 1px);
      background-size: 28px 20px;
    }

    .input-key {
      min-height: 42px;
      background: #eef7ff;
      box-shadow: inset 0 1px 6px rgba(63, 125, 180, 0.35);
    }

    .btn {
      border: 1px solid #aeb9c6;
      border-radius: 6px;
      background: #f5f8fb;
      color: #14213d;
      padding: 6px 11px;
      font-size: 13px;
      cursor: pointer;
      margin-top: 8px;
    }

    .btn:hover {
      background: #e8f1f8;
    }

    .btn-danger {
      color: #7d1b1b;
    }

    .btn-save {
      color: #14213d;
    }

    .note {
      margin: 0 0 8px 0;
      font-size: 13px;
      color: #253b55;
    }

    @media (max-width: 800px) {
      .key-row {
        grid-template-columns: 1fr;
        gap: 8px;
      }
    }
  </style>


</head>

<body>

<div class="page-header">
  <h2>Quản lý khóa ký đơn hàng</h2>


  <div class="user-info">
    <span>ID: <%= idUser != null ? idUser : "null" %></span>
    <span class="user-icon">👤</span>
  </div>


</div>

<% if (message != null) { %> <div class="message"><%= message %></div>
<% } %>

<div class="card">
  <h3>Public key hiện tại</h3>


  <% if (activeKey != null) { %>
  <div class="key-row">
    <div class="field">
      <b>ID khóa:</b> <%= activeKey.getId() %>
    </div>

    <div class="field">
      <b>Trạng thái:</b>
      <span class="badge <%= statusClass %>"><%= status %></span>
    </div>

    <div class="field">
      <b>Public key:</b>
      <textarea class="public-key-box" rows="4" readonly><%= activeKey.getPublicKey() != null ? activeKey.getPublicKey() : "" %></textarea>
    </div>
  </div>

  <form method="post" action="<%= request.getContextPath() %>/KeyManagementController">
    <input type="hidden" name="action" value="reportLost">
    <button type="submit" class="btn btn-danger">× Báo mất khóa</button>
  </form>
  <% } else { %>
  <p class="empty">Chưa có public key đang hoạt động.</p>
  <% } %>


</div>

<div class="card">
  <h3>Thêm public key mới</h3>

  <form method="post" action="<%= request.getContextPath() %>/KeyManagementController">
    <input type="hidden" name="action" value="addPublicKey">

    <p class="note">Dán public key được tạo từ tool vào ô bên dưới:</p>

    <textarea class="input-key" name="publicKey" rows="3"></textarea>

    <br>

    <button type="submit" class="btn btn-save">✓ Lưu public key</button>
  </form>


</div>

</body>
</html>
