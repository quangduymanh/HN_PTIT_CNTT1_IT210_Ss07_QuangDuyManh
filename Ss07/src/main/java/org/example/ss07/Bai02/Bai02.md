#1. Phân tích logic
   Vi phạm nguyên tắc gì?

Đoạn code:

model.addAttribute("categories", categories);

bị lặp lại ở nhiều method → vi phạm nguyên tắc DRY (Don't Repeat Yourself).

Rủi ro khi mở rộng hệ thống

Nếu có 20 trang đều dùng danh sách này:

Phải sửa ở nhiều nơi → dễ sót
Code khó bảo trì
Dễ gây lỗi không đồng nhất dữ liệu
Controller bị dài và rối
#2. Sửa bằng @ModelAttribute
   Ý tưởng:
   Viết 1 method dùng @ModelAttribute
   Spring sẽ tự động add vào model cho tất cả request