<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String message = (String) request.getAttribute("message");
    String resultCode = (String) request.getAttribute("resultCode");
    Object idDonHang = request.getAttribute("idDonHang");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xác thực chữ ký đơn hàng</title>

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
            margin: 0 0 20px 0;
            font-size: 32px;
            color: #071a38;
        }

        .note {
            color: #253b55;
            font-size: 15px;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-top: 14px;
            margin-bottom: 7px;
            font-size: 16px;
            font-weight: bold;
        }

        input {
            width: 100%;
            height: 46px;
            padding: 9px 12px;
            border: 1px solid #9ebbd8;
            border-radius: 7px;
            font-size: 16px;
            outline: none;
        }

        textarea {
            width: 100%;
            min-height: 220px;
            padding: 10px 12px;
            border: 1px solid #9ebbd8;
            border-radius: 7px;
            font-family: Consolas, "Courier New", monospace;
            font-size: 14px;
            outline: none;
            resize: vertical;
        }

        input:focus,
        textarea:focus {
            border-color: #5b9dd9;
            box-shadow: 0 0 8px rgba(91, 157, 217, 0.45);
        }

        button {
            margin-top: 18px;
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

        .message {
            padding: 12px 14px;
            border-radius: 7px;
            margin-bottom: 18px;
            font-size: 17px;
        }

        .success {
            background: #e9f8ef;
            border: 1px solid #91d6a5;
            color: #146b2e;
        }

        .error {
            background: #fff0f0;
            border: 1px solid #e0a1a1;
            color: #a52222;
        }
    </style>
</head>
<body>

<div class="card">
    <h2>Xác thực chữ ký đơn hàng</h2>

    <p class="note">
        Dùng tool để ký file JSON đã tải từ đơn hàng. Sau đó copy chữ ký Base64 từ tool và dán vào form bên dưới.
    </p>

    <% if (message != null) { %>
    <div class="message <%= "VALID".equals(resultCode) ? "success" : "error" %>">
        <%= message %>
    </div>
    <% } %>

    <form method="post" action="<%= request.getContextPath() %>/SubmitSignatureController">
        <label>ID đơn hàng</label>
        <input type="number" name="idDonHang" value="<%= idDonHang != null ? idDonHang : "" %>">

        <label>Chữ ký Base64 từ tool</label>
        <textarea name="signature"></textarea>

        <br>

        <button type="submit">Xác thực chữ ký</button>
    </form>
</div>

</body>
</html>