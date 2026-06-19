package com.edu.hcmuaf.fit.webbanvemaybay.dao;

import com.edu.hcmuaf.fit.webbanvemaybay.models.User;
import org.jdbi.v3.core.Jdbi;

import java.time.LocalDateTime;

public class UserDAO extends DBContext {

    // tạo user mới và db
//    public String addUser(User user) {
//        try {
//            Jdbi jdbi = get();
//
//            //  kiểm tra xem có user trong db chưa
//            int isUser = jdbi.withHandle(h -> {
//                String q = "select count(*) from users where username = :username";
//                return h.createQuery(q).bind("username", user.getUsername()).mapTo(Integer.class).one();
//            });
//            if (isUser == 0) {
//                int soDong = jdbi.withHandle(h -> {
//                    String q = "";
//                    h.createUpdate(q);
//                })
//            }
//
//        }
//    }


    public String addUser(User user) {
        try {
            Jdbi jdbi = get();
            boolean isUserInDb = jdbi.withHandle(h -> {
                return h.createQuery("select count(*) from users where username=:username").bind("username", user.getUsername()).mapTo(Integer.class).one() > 0;
            });
            if (isUserInDb) {
                return "username đã tồn tại";
            }
            int res = jdbi.withHandle(h -> {
                return h.createUpdate("insert into users (id, username, password, so_dien_thoai, email, code_xac_thuc, hang_xac_thuc, role, status)" +
                        " values (:id, :username, :password, :soDienThoai, :email, :codeXacThuc, :hangXacThuc, :role, :status)").bindBean(user).executeAndReturnGeneratedKeys().mapTo(Integer.class).one();
            });
            jdbi.withHandle(h -> {
                return h.createUpdate("insert into thong_tin_nguoi_dung (id_user, ho, ten, dia_chi, gioi_tinh, ngay_sinh)" +
                        " values (:idUser, null, null, null, null,null)").bind("idUser", res).execute();
            });

            jdbi.withHandle(h -> {
                return h.createUpdate("insert into ve_da_dat (id_user, so_luong, id_ve, ngay_dat)" +
                        " values (:idUser, null, null, null)").bind("idUser", res).execute();
            });
            return res > 0 ? "thêm tài khoảng thành công" : "thêm tài khoảng thất bại";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    public User kiemTraUser(String username, String password) {
        try {
            Jdbi jdbi = get();
            User user = jdbi.withHandle(h-> {
                String q = "select * from users where username=:username and password=:password";
                return h.createQuery(q).bind("username", username).bind("password", password).mapToBean(User.class).findFirst().orElse(null);
            });
            if (user != null) {
                return user;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isUserAndEmailExist(String username, String email) {
        try {
            Jdbi jdbi = get();
            int soLuong = jdbi.withHandle(h -> {
                String q = "select count(*) from users where username=:username and email=:email";
                return h.createQuery(q).bind("username", username).bind("email", email).mapTo(Integer.class).one();
            });
            if (soLuong > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean themMaXacThuc(String username, String maXacThuc, LocalDateTime hangXacThuc) {
        try {
            Jdbi jdbi = get();
            int soLuong = jdbi.withHandle(h -> {
                String q = "update users set code_xac_thuc=:maXacThuc, hang_xac_thuc=:hangXacThuc where username=:username";
                return h.createUpdate(q).bind("maXacThuc", maXacThuc).bind("hangXacThuc", hangXacThuc).bind("username", username).execute();
            });
            if (soLuong > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isEmailExist(String email) {
        try {
            Jdbi jdbi = get();
            int soLuong = jdbi.withHandle(h -> {
                String q = "select count(*) from users where email=:email";
                return h.createQuery(q).bind("email", email).mapTo(Integer.class).one();
            });
            if (soLuong > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String doiMatKhau(User user) {
        try {
            Jdbi jdbi = get();
            LocalDateTime hangXacThuc = jdbi.withHandle(h -> {
                String q = "select hang_xac_thuc from users where username=:username";
                return h.createQuery(q).bind("username", user.getUsername()).mapTo(LocalDateTime.class).one();
            });
            if (LocalDateTime.now().isBefore(hangXacThuc)) {
                user.setCodeXacThuc(null);
                user.setHangXacThuc(null);
                int soLuong = jdbi.withHandle(h -> {
                    String q = "update users set password=:password, code_xac_thuc=:codeXacThuc, hang_xac_thuc=:hangXacThuc where username=:username";
                    return h.createUpdate(q).bind("password", user.getPassword()).bind("codeXacThuc", user.getCodeXacThuc())
                            .bind("hangXacThuc", user.getHangXacThuc()).bind("username", user.getUsername()).execute();
                });
                if (soLuong > 0) {
                    return "đổi mật khẩu thành công";
                }
                return "đổi mật khẩu thất bại do lỗi hệ thống";
            }
            return "code xác thực đã hết hạng";
        } catch (Exception e) {
            e.printStackTrace();
            return "đổi mật khẩu thất bại do lỗi hệ thống";
        }
    }

    public User getUserById(String id) {
        try {
            Jdbi jdbi = get();
            return jdbi.withHandle(h -> {
                String q = "select * from users where id=:id";
                return h.createQuery(q).bind("id", id).mapToBean(User.class).findFirst().orElse(null);
            });

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateEmailById(String idUser, String newEmail) {
        try {
            Jdbi jdbi = get();
            int soLuong = jdbi.withHandle(h -> {
                String q = "update users set email=:newEmail where id=:id";
                return h.createUpdate(q).bind("newEmail", newEmail).bind("id", idUser).execute();
            });
            return soLuong > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verifyUser(String email, String code) {
        try {
            Jdbi jdbi = get();
            int soLuong = jdbi.withHandle(h -> {
                String q = "select count(*) from users where email=:email and code_xac_thuc=:codeXacThuc";
                return h.createQuery(q).bind("email", email).bind("codeXacThuc", code).mapTo(Integer.class).one();
            });
            if (soLuong > 0) {
                return jdbi.withHandle(h -> {
                    String q = "update users set code_xac_thuc= null, status=1 where email=:email and code_xac_thuc=:codeXacThuc";
                    return h.createUpdate(q).bind("codeXacThuc", code).bind("email", email).execute();
                }) > 0;
            }
            return false;
        }  catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public User getUserByEmail(String email) {
        try {
            Jdbi jdbi = get();
            return jdbi.withHandle(h -> {
                String q = "select * from users where email=:email";
                return h.createQuery(q).bind("email", email).mapToBean(User.class).findFirst().orElse(null);
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean updatePassword(int userId, String newPassword) {
        try {
            org.jdbi.v3.core.Jdbi jdbi = get();
            return jdbi.withHandle(h -> {
                String q = "UPDATE users SET password = :password WHERE id = :id";
                return h.createUpdate(q)
                        .bind("password", newPassword)
                        .bind("id", userId)
                        .execute();
            }) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public String getPasswordById(int userId) {
        try {
            org.jdbi.v3.core.Jdbi jdbi = get();
            return jdbi.withHandle(h -> {
                String q = "SELECT password FROM users WHERE id = :id";
                return h.createQuery(q)
                        .bind("id", userId)
                        .mapTo(String.class)
                        .findFirst()
                        .orElse(null);
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
