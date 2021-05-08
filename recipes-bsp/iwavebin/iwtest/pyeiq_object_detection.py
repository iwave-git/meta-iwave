import gi
gi.require_version("Gtk", "3.0")
from gi.repository import Gtk
import os
import socket
from subprocess import Popen
import sys
import threading
import time


try:
    import eiq
    PYEIQ = True
except ImportError:
    PYEIQ = False


BIN = "/home/root/.cache/eiq/eIQObjectDetection/model/coco_labels.txt"


def check_connection(host="208.67.220.220", port=53, timeout=5):
    time.sleep(1)
    try:
        socket.setdefaulttimeout(timeout)
        socket.socket(socket.AF_INET, socket.SOCK_STREAM).connect((host, port))
        return True
    except socket.error as ex:
        print(ex)
        return False


class pyeIQObjectDetection(Gtk.Window):
    def __init__(self):
        Gtk.Window.__init__(self, title="pyeIQ Object Detection")
        self.set_default_size(1080, 1920)

        box = Gtk.Box()
        box.set_homogeneous(True)

        self.label = Gtk.Label.new("Starting...")
        box.pack_start(self.label, True, True, 0)
        self.add(box)

        self.label.set_text("Checking for internet connection...")

        thread = threading.Thread(target=self.check)
        thread.daemon = True
        thread.start()
    
    def check(self):
        time.sleep(1)
        if check_connection():
            self.label.set_text("Downloading pyeIQ and Object Detection data, it may take a while...")
            Popen(["pip3", "install", "eiq"]).wait()
            Popen(["pyeiq", "--run", "object_detection_tflite", "-v /dev/video0", "-r full_hd", "-f opencv"]).wait()
        else:
            self.label.set_text("No internet connection.")
  
        thread = threading.Thread(target=self.terminate)
        thread.daemon = True
        thread.start()

    def terminate(self):
        time.sleep(5)
        Gtk.main_quit()

        
if __name__ == "__main__":
    if PYEIQ and os.path.isfile(BIN):
        Popen(["pyeiq", "--run", "object_detection_tflite", "-v /dev/video0", "-r full_hd", "-f opencv"]).wait()
        sys.exit()

    window = pyeIQObjectDetection()
    window.connect("destroy", Gtk.main_quit)
    window.show_all()
    Gtk.main()
